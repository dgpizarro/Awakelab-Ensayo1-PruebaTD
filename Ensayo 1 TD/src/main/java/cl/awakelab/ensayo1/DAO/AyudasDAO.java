package cl.awakelab.ensayo1.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import cl.awakelab.ensayo1.beans.Ayudas;
import cl.awakelab.ensayo1.beans.Beneficiarios;
import cl.awakelab.ensayo1.beans.Ciudades;

public class AyudasDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    static Logger log = Logger.getLogger(AyudasDAO.class);

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

    public Ayudas obtenerAyudaById(int id) {
        
        try {
            String sql = "select ayudaid, nombreciudad, motivo, monto from ayudas inner join "
                    + "beneficiarios on (ayudas.beneficiarioid = beneficiarios.beneficiarioid) "
                    + "inner join ciudades on (beneficiarios.ciudadid = ciudades.ciudadid) where ayudaid = ?";
            return template.queryForObject(sql, new Object[] { id }, new AyudasMapper());
        } catch (Exception e) {
            return null;
        }
        
    }

    public Boolean crearAyuda2(final Ayudas a) {

        String sql = "insert into ayudas (beneficiarioid, motivo, monto) values(?,?,?)";

        return template.execute(sql, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, 
            DataAccessException {

                ps.setInt(1, a.getBeneficiario().getBeneficiarioid());
                ps.setString(2, a.getMotivo());
                ps.setInt(3, a.getMonto());

                return ps.execute();

            }
        });
    }

}
