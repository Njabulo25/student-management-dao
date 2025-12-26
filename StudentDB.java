
package za.ac.tut.bl;

import java.util.List;
import za.ac.tut.entity.Student;
import java.sql.*;
import java.util.ArrayList;

public class StudentDB implements DAO<Student>
{
    private Connection connection;

    public StudentDB(String dbURL,String username,String password) throws SQLException
    {
        connection = getConnection(dbURL,username,password);
    }
    
    
    //Helper methods.
    @Override
    public Student get(Integer code)
    {
        String sql = "SELECT StudentNumber, name, surname " +
                     "FROM StudentTable " +
                     "WHERE StudentNumber = ?";
        
        
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1,code);
            try(ResultSet rs = ps.executeQuery())
            {
                if(rs.next())
                {
                    Integer studentNumber = rs.getInt("StudentNumber");
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    
                    return new Student(studentNumber,name,surname);
                }
            }
        } catch (SQLException ex)
        {
            System.getLogger(StudentDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            
        }
       return null;
    }

    @Override
    public List<Student> getAll()
    {
        String sql = "SELECT * " + 
                     "FROM StudentTable";
        
        List<Student> students = new ArrayList<Student>();
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    Integer studentNumber = rs.getInt("StudentNumber");
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    
                    Student student = new Student(studentNumber,name,surname);
                    
                    students.add(student);
                }
                return students;
            }
        }
        catch (SQLException ex)
        {
            System.getLogger(StudentDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return null;
        }
    }

    @Override
    public boolean add(Student t)
    {
        String sql = "INSERT INTO StudentTable (StudentNumber, name, surname) " +
                     "VALUES (?, ?, ?)";
        
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, t.getStudNum());
            ps.setString(2, t.getName());
            ps.setString(3,t.getSurname());
            ps.executeUpdate();
            
            return true;   
        }
        catch (SQLException ex)
        {
            System.getLogger(StudentDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
        
    }

    @Override
    public boolean update(Student t)
    {
        String sql = "UPDATE StudentTable " +
                     "SET Name = ?, Surname = ? " +
                     "WHERE StudentNumber = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, t.getName());
            ps.setString(2,t.getSurname());
            ps.setInt(3, t.getStudNum());
            ps.executeUpdate();
            return true;
            
        }
        catch (SQLException ex)
        {
            System.getLogger(StudentDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Student t)
    {
        String sql = "DELETE FROM StudentTable " +
                     "WHERE StudentNumber = ?";
        
        try(PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setInt(1, t.getStudNum());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException ex) {
            System.getLogger(StudentDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return false;
        }
    }

    private Connection getConnection(String dbURL, String username, String password) throws SQLException
    {
        Connection theConnection = DriverManager.getConnection(dbURL,username,password);
        return theConnection;
    }
    
}
