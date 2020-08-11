package cl.awakelab.ensayo1.DAO;

import org.springframework.jdbc.core.JdbcTemplate;

public class CiudadesDAO {
    
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

}
