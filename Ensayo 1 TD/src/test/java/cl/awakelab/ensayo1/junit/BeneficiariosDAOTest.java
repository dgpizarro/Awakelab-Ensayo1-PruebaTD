package cl.awakelab.ensayo1.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.awakelab.ensayo1.DAO.BeneficiariosDAO;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BeneficiariosDAOTest {

    @Autowired
    BeneficiariosDAO bd;
    

    @Test
    public void testListarBeneficiarios() {
        List<Beneficiarios> lbenef = bd.listarBeneficiarios();
        assertNotNull(lbenef.size());
    }

}
