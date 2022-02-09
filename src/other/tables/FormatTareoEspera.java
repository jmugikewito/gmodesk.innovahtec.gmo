package other.tables;

/**

 @author jmugi
 */
import color.MaterialColor;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatTareoEspera extends DefaultTableCellRenderer {

    public FormatTareoEspera() {

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setEnabled(table == null || table.isEnabled());

        switch (String.valueOf(value)) {
            case "0001":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cultivo/grape24.png")));
                setBackground(MaterialColor.PURPLE_200);
                setForeground(MaterialColor.PURPLE_200);
                break;
            case "0002":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cultivo/arandano24.png")));
                setBackground(MaterialColor.INDIGO_200);
                setForeground(MaterialColor.INDIGO_200);
                break;
            case "0003":
            case "0005":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cultivo/esparrago24.png")));
                setBackground(MaterialColor.GREEN_200);
                setForeground(MaterialColor.GREEN_200);
                break;
            case "0006":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cultivo/avocado24.png")));
                setBackground(MaterialColor.GREEN_200);
                setForeground(MaterialColor.GREEN_200);
                break;
            case "0007":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cultivo/frambuesa24.png")));
                setBackground(MaterialColor.GREEN_200);
                setForeground(MaterialColor.GREEN_200);
                break;
            case "":
            case "null":
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/alert24Red.png")));
                setBackground(MaterialColor.REDA_100);
                setForeground(MaterialColor.REDA_100);
                break;
            default:
                setIcon(null);
                setBackground(MaterialColor.WHITE);
                setForeground(MaterialColor.BLACK);
        }

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        return this;
    }
}
