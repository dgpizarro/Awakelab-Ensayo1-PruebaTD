package cl.awakelab.ensayo1.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;
import cl.awakelab.ensayo1.beans.Ciudades;

public class AyudasDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public class AyudasMapper implements RowMapper<Ayudas> {
        public Ayudas mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ayudas a = new Ayudas();
            Beneficiarios b = new Beneficiarios();
            Ciudades c = new Ciudades();
            a.setAyudaid(rs.getInt(1));
            c.setNombreciudad(rs.getString(2));
            a.setMotivo(rs.getString(3));
            a.setMonto(rs.getInt(4));
            b.setCiudad(c);
            a.setBeneficiario(b);
            return a;
        }
    }

    public List<Ayudas> listarAyudas() {
        String sql = "select ayudaid, nombreciudad, motivo, monto from ayudas inner join "
                + "beneficiarios on (ayudas.beneficiarioid = beneficiarios.beneficiarioid) "
                + "inner join ciudades on (beneficiarios.ciudadid = ciudades.ciudadid)";
        return template.query(sql, new AyudasMapper());
    }
    
    public int crearAyuda (Ayudas a) {
        String sql ="INSERT INTO ayudas (beneficiarioid, motivo, monto) VALUES ( "
                + a.getBeneficiario().getBeneficiarioid() + ",'" + a.getMotivo() + "'," + a.getMonto() +")";
        return template.update(sql);
    }

}
