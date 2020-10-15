package data;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Cursos;

public class DataCursos {
    
    int id = 0;

    public List<Cursos> list(String filter) {
        List<Cursos> lista = new ArrayList<Cursos>();
        String sql = "SELECT * FROM cursos ";
        try {
            Statement st = Coneccion.connectSQLite().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Cursos c = new Cursos();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setClase(rs.getString("clase"));
                c.setCreditos(rs.getInt("creditos"));
                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return lista;
    }

    public void crear(Cursos d) {
        String sql = "INSERT INTO cursos (nombre, descripcion, clase, creditos) " + "VALUES(?,?,?,?) ";
        int i = 0;
        int res = 0;
        try {
            PreparedStatement ps = Coneccion.connectSQLite().prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getDescripcion());
            ps.setString(++i, d.getClase());
            ps.setInt(++i, d.getCreditos());
            res= ps.executeUpdate();// 0 no o 1 si 
            System.out.println("se creo: "+ res + " Cursos");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public Cursos get(int id) {
        Cursos c = new Cursos();
        List<Cursos> lis2 = new ArrayList<Cursos>();
        String sql = "SELECT * FROM cursos WHERE id = "+id+" ";
        try {
            Statement st = Coneccion.connectSQLite().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                c.setClase(rs.getString("clase"));
                c.setCreditos(rs.getInt("creditos"));
                lis2.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return c;
    }

    // update(Person, int): void (o update(Person): void)
    public void update(Cursos d) {
        String sql = "UPDATE cursos SET "
                + "nombre = ?, "
                + "descripcion = ?, "
                + "clase = ?, "
                + "creditos = ?"
                + "WHERE id = ? ";
        int i = 0;
        int res = 0;
        try {
            PreparedStatement ps = Coneccion.connectSQLite().prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getDescripcion());
            ps.setString(++i, d.getClase());
            ps.setInt(++i, d.getCreditos());
            ps.setInt(++i, d.getId());
            res= ps.executeUpdate();// 0 no o 1 si 
            System.out.println("Se actualizo: "+ res + " Cursos");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return ;
    }

    public void delete(int id) {
         String sql = "DELETE FROM cursos WHERE id = ?";
        int i = 0;
        int res = 0;
        try {
            PreparedStatement ps = Coneccion.connectSQLite().prepareStatement(sql);
            ps.setInt(1, id );
            res= ps.executeUpdate();// 0 no o 1 si 
            System.out.println("Se elimino: "+ res + " Cursos");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
        return ;
    }
}