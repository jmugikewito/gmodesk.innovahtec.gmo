package other.tables;

/**

 @author jmugi
 */
import color.MaterialColor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatAdminTareosNisira extends DefaultTableCellRenderer {

    private boolean esNisira = false;

    public FormatAdminTareosNisira(boolean isNisira) {
        this.esNisira = isNisira;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled());
        if (esNisira) {
            switch (column) {
                case 12:
                    if (table.getValueAt(row, column).toString().equals("CON ASIST")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asistEnabledIndigo24.png")));
                        setBackground(MaterialColor.GREY_200);
                        setForeground(MaterialColor.BLACK);
                    } else if (table.getValueAt(row, column).toString().equals("SIN ASIST")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/asistDisabledRed24.png")));
                        setBackground(MaterialColor.GREY_200);
                        setForeground(MaterialColor.BLACK);
                    }
                    break;
                default:
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setIcon(null);
            }
        } else {
            switch (column) {
                case 2:
                    if (table.getValueAt(row, column).toString().toLowerCase().contains("unknow")
                            || table.getValueAt(row, column).toString().toLowerCase().contains("desconocido")
                            || table.getValueAt(row, column).toString().toLowerCase().contains("no registrado")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
                        setBackground(MaterialColor.RED_200);
                        setForeground(MaterialColor.BLACK);
                    } else {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_normal.png")));
                        setBackground(MaterialColor.WHITE);
                        setForeground(MaterialColor.BLACK);
                    }
                    break;

                default:
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    setIcon(null);
            }
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}
