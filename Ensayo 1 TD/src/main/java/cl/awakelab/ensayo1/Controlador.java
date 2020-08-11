package cl.awakelab.ensayo1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.awakelab.ensayo1.DAO.AyudasDAO;
import cl.awakelab.ensayo1.DAO.BeneficiariosDAO;
import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@Controller
public class Controlador {

    @Autowired
    AyudasDAO ad;

    @Autowired
    BeneficiariosDAO bd;

    @RequestMapping("/index")
    public String irPortal(Model m) {
        List<Ayudas> layudas = ad.listarAyudas();
        List<Beneficiarios> lbenef = bd.listarBeneficiarios();
        m.addAttribute("ayudas", layudas);
        m.addAttribute("beneficiarios", lbenef);
        m.addAttribute("command", new Ayudas());
        return "Resumen";
    }

    @RequestMapping(value = "/agregarAyuda", method = RequestMethod.POST)
    public String registrarAyuda(Ayudas a, RedirectAttributes r) {
        ad.crearAyuda(a);
        String alert = "open";
        r.addFlashAttribute("open", alert);
        return "redirect:/index.html";
    }

}
