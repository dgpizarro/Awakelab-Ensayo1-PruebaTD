package cl.awakelab.ensayo1.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cl.awakelab.ensayo1.beans.Beneficiarios;

public class BeneficiariosDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    static Logger log = Logger.getLogger(BeneficiariosDAO.class);

    public class BeneficiariosMapper implements RowMapper<Beneficiarios> {
        public Beneficiarios mapRow(ResultSet rs, int rowNum) throws SQLException {
            Beneficiarios b = new Beneficiarios();
            b.setBeneficiarioid(rs.getInt(1));
            b.setNombre(rs.getString(2));
            return b;
        }
    }

    public List<Beneficiarios> listarBeneficiarios() {
        String sql = "select beneficiarioid, nombre from beneficiarios";
        return template.query(sql, new BeneficiariosMapper());
    }

}
