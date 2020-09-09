package cl.awakelab.ensayo1.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.awakelab.ensayo1.DAO.AyudasDAO;
import cl.awakelab.ensayo1.DAO.BeneficiariosDAO;
import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@RestController
public class ControladorRest {
    
    @Autowired
    AyudasDAO ad;
    
    @Autowired
    BeneficiariosDAO bd;
    
    static Logger log = Logger.getLogger(ControladorRest.class);
    
    @RequestMapping(value = "/api/listab", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Beneficiarios> getBeneficiarios() {
        try {
            List<Beneficiarios> lbenef = bd.listarBeneficiarios();
            log.log(Level.INFO, "Carga exitosa listado beneficiarios desde base de datos.");
            return lbenef;
        } catch (Exception e) {
            log.log(Level.ERROR, e);
            return null;
        }
            
    }
    
    @RequestMapping(value = "/api/listaAyudas", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Ayudas> getAyudas() {
            List<Ayudas> layudas = ad.listarAyudas();
            return layudas;
    }

}
