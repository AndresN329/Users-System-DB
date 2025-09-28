package controller;

import entity.Coder;
import model.CoderModel;
import utils.InputRequester;
import utils.JOptionPaneUtils;

import javax.swing.*;
import java.util.List;

public class CoderController {

    public static void createCoder() {
        CoderModel objCoderModel = new CoderModel();

        Coder objCoder = new Coder();

        objCoder.setName(InputRequester.requestString("Enter coder's name: "));
        objCoder.setAge(InputRequester.requestInteger("Enter coder's age: "));
        objCoder.setClan(InputRequester.requestString("Enter coder's clan: "));

        objCoder = (Coder) objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());

    }

    public static void dispalyCoders(){
        CoderModel coderModel = new CoderModel();
        List<Object> coders = coderModel.findAll();

        if (coders.isEmpty()) {
            JOptionPane.showMessageDialog(null,"The DB is empty");
            return;
        }

        String[] columNames = {"Id","Name","Age","Clan"};
        Object[][] data = new Object[coders.size()][4];

        for (int i = 0; i < coders.size(); i++) {
            Coder coder = (Coder) coders.get(i);
            data[i][0] = coder.getId();
            data[i][1] = coder.getName();
            data[i][2] = coder.getAge();
            data[i][3] = coder.getClan();
        }
        JOptionPaneUtils.showTable("Coder's DB", columNames, data);
    }

    public static void UpdateCoder() {
        CoderModel coderModel = new CoderModel();

        dispalyCoders();
        int idselected = InputRequester.requestInteger("Enter the coder's Id you want update");
        Coder objCoder = coderModel.FindById(idselected);

        if(objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            objCoder.setName(JOptionPane.showInputDialog("Enter coder's new name", objCoder.getName()));
            objCoder.setAge( Integer.parseInt(JOptionPane.showInputDialog("Enter coder's new age", objCoder.getAge())));
            objCoder.setClan(JOptionPane.showInputDialog("Enter coder's new clan", objCoder.getClan()));

            coderModel.update(objCoder);
        }
    }

    public static void DeleteCoder() {
        CoderModel coderModel = new CoderModel();

        dispalyCoders();
        int IdCoder = InputRequester.requestInteger("Enter the coder's id you want delete");

        Coder objCoder = coderModel.FindById(IdCoder);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder not found");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want delete this coder?");
            if (confirm == 0) coderModel.delete(objCoder);
        }
    }
}
