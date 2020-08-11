package cl.awakelab.ensayo1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.awakelab.ensayo1.DAO.BeneficiariosDAO;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@RestController
public class ControladorJson {
    
    @Autowired
    BeneficiariosDAO bd;
    
    @RequestMapping(value = "/listab", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE }, method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Beneficiarios> getBeneficiarios() {
            List<Beneficiarios> lbenef = bd.listarBeneficiarios();
            return lbenef;
    }

}
