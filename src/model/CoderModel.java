package model;

import database.CRUD;
import database.ConfigDB;
import entity.Coder;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoderModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        Connection objConnection = ConfigDB.openConnection();

        Coder objCoder = (Coder) obj;

        try{
            String sql = "INSERT INTO coder (name,age, clan) VALUES (?,?,?)";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //assign the values
            objPrepare.setString(1, objCoder.getName());
            objPrepare.setInt(2, objCoder.getAge());
            objPrepare.setString(3, objCoder.getClan());

            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()) {
                objCoder.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null,"Coder was succesfully added");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public List<Object> findAll() {

        List<Object> listCoders = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try{
            String sql = "SELECT * FROM coder";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                Coder objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("clan"));
                listCoders.add(objCoder);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return listCoders;
    }

    public Coder FindById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = null;

        try {
            String sql = "SELECT * FROM coder WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            if (objResult.next()) {
                objCoder = new Coder();
                objCoder.setId(objResult.getInt("id"));
                objCoder.setName(objResult.getString("name"));
                objCoder.setAge(objResult.getInt("age"));
                objCoder.setClan(objResult.getString("clan"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public boolean update(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Coder objCoder = (Coder) obj;
        boolean isUpdate = false;

        try {
            String sql = "UPDATE coder SET name = ?, age = ?, clan = ? WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setString(1, objCoder.getName());
            objPrepare.setInt(2, objCoder.getAge());
            objPrepare.setString(3, objCoder.getClan());
            objPrepare.setInt(4, objCoder.getId());

            int objResult = objPrepare.executeUpdate();

            if(objResult > 0) {
                JOptionPane.showMessageDialog(null, "The coder" + objCoder.getName() + "was update");
                isUpdate = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();

        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Coder objCoder = (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();
        boolean isDeleted = false;

        try {
            String sql = "DELETE FROM coder WHERE id = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, objCoder.getId());
            int objResult = objPrepare.executeUpdate();

            if (objResult > 0) {
                JOptionPane.showMessageDialog(null, "The coder " + objCoder.getName() + " was removed from the DB");
                isDeleted = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

}