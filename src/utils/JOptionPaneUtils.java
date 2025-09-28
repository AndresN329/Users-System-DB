package utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.Arrays;

public class JOptionPaneUtils {
    public static void ShowMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public static void showTable(String title, String[] columnNames, Object[][] data) {
        int lastCol = Math.max(0, columnNames.length - 1);
        showTable(title, columnNames, data, new int[]{lastCol});
    }

    // Método flexible: especificas qué columnas deben tener multi-línea (indices 0-based)
    public static void showTable(String title, String[] columnNames, Object[][] data, int[] multilineCols) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla no editable
            }
        };

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Monospaced", Font.PLAIN, 12));
        table.setRowHeight(24); // altura mínima

        // Ajustes iniciales de ancho de columna (puedes adaptar estos valores)
        TableColumnModel colModel = table.getColumnModel();
        for (int i = 0; i < columnNames.length; i++) {
            int pref = 100; // valor por defecto
            if (i == 0) pref = 50;            // ID
            if (i == 1) pref = 180;           // Product
            if (i == 2) pref = 100;           // Price
            if (i == 3) pref = 80;            // Stock
            colModel.getColumn(i).setPreferredWidth(pref);
        }

        // Aplica renderer multi-línea en las columnas solicitadas
        for (int col : multilineCols) {
            if (col >= 0 && col < colModel.getColumnCount()) {
                colModel.getColumn(col).setCellRenderer(new TextAreaRenderer());
                // dar más ancho a la columna de descripción si queremos
                colModel.getColumn(col).setPreferredWidth(300);
            }
        }

        // Ajustar la altura de filas en función del contenido de las columnas multilínea
        // (usa prepareRenderer para obtener componente y su preferredSize)
        for (int row = 0; row < table.getRowCount(); row++) {
            int maxHeight = table.getRowHeight(); // empieza por la mínima
            for (int col : multilineCols) {
                if (col >= 0 && col < table.getColumnCount()) {
                    TableCellRenderer renderer = table.getCellRenderer(row, col);
                    Component comp = table.prepareRenderer(renderer, row, col);
                    int prefHeight = comp.getPreferredSize().height;
                    maxHeight = Math.max(maxHeight, prefHeight);
                }
            }
            if (maxHeight > table.getRowHeight()) {
                table.setRowHeight(row, maxHeight);
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 420));

        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Renderer que usa JTextArea para permitir wrap y varias líneas
    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value == null ? "" : value.toString());
            setFont(table.getFont());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            // fijar el ancho del textArea para que el preferredSize calcule altura correcta
            int colWidth = table.getColumnModel().getColumn(column).getWidth();
            setSize(new Dimension(colWidth, Integer.MAX_VALUE));

            return this;
        }
    }




}