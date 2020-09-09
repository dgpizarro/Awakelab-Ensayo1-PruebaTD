package cl.awakelab.ensayo1.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.awakelab.ensayo1.DAO.AyudasDAO;
import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@Controller
public class Controlador {
    
    static Logger log = Logger.getLogger(Controlador.class);

    @Autowired
    AyudasDAO ad;
   
    @RequestMapping({"/index", "/"})
    public String irPortal(Model m, HttpServletRequest r) {
        
        try {
            List<Ayudas> layudas = ad.listarAyudas();
            m.addAttribute("ayudas", layudas);
            log.log(Level.INFO, "Carga exitosa listado ayudas desde base de datos.");
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }
        
        m.addAttribute("command", new Ayudas());
        
        
        try {
            String scheme = r.getScheme();
            String host = r.getLocalAddr();
            int port = r.getLocalPort();
            String path = "/ensayo1/api/listab";
            
            final URI uri= new URI(scheme, null, host, port, path, null, null);
            
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Beneficiarios>> benefResponse =
                    restTemplate.exchange(uri,
                                HttpMethod.GET, null, new ParameterizedTypeReference<List<Beneficiarios>>() {
                        });
            List<Beneficiarios> lbenef = benefResponse.getBody();
            m.addAttribute("beneficiarios", lbenef);
            log.log(Level.INFO, "Consumo exitoso listado beneficiarios Json");
            
        } catch (Exception e) {
            log.log(Level.ERROR, e);
            e.printStackTrace();
        } 
        
        log.log(Level.INFO, "Ingreso a index");
        return "Resumen";
    }

    @RequestMapping(value = "/agregarAyuda", method = RequestMethod.POST)
    public String registrarAyuda(Ayudas a, RedirectAttributes r) {
        String alert;
        
        try {
            ad.crearAyuda2(a);
            log.log(Level.INFO, "Ejecución SQL: insert into ayudas (beneficiarioid, motivo, monto) values ( " +
            a.getBeneficiario().getBeneficiarioid() + ", " + a.getMotivo() + ", " + a.getMonto() +" )");
            alert = "ok";
            
        } catch (Exception e) {
            alert = "wrong";
            log.log(Level.ERROR, e);
        }
               
        r.addFlashAttribute("open", alert);        
        log.log(Level.INFO, "Ingreso a index");
        return "redirect:/index";
    }

}
