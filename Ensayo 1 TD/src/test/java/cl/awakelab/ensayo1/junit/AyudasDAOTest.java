package cl.awakelab.ensayo1.junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cl.awakelab.ensayo1.DAO.AyudasDAO;
import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AyudasDAOTest  {

    @Autowired
    AyudasDAO ad;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testObtenerAyudaById() {
        Ayudas a = ad.obtenerAyudaById(1);
        assertEquals("Insumos", a.getMotivo());
    }

    @Test
    public void testCrearAyuda() {
        Ayudas a = new Ayudas();
        Beneficiarios b = new Beneficiarios();
        a.setMotivo("Prueba JUnit");
        a.setMonto(1000);
        b.setBeneficiarioid(1);
        a.setBeneficiario(b);
        
        ad.crearAyuda2(a);
        // assertEquals("Prueba JUnit", ad.obtenerAyudaById(??).getMotivo());
    }

    @Test
    public void testListarAyudas() {
        List<Ayudas> layudas = ad.listarAyudas();
        assertNotNull(layudas.size());
    }

}
