package gmo.dialog;

import app.RunMain;
import static gmo.core.MainLite.*;
import color.MaterialColor;
import gmo.utils.jkeys;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmugi.voids.JCallback;
import kevin.component.defaults;
import kevin.component.toast.Toast;
import kevin.component.toast.Toast.Style;
import utils.ExecHTTP;
import utils.jsonmethods;

/**

 @author kevin leandro
 */
public class SendNisira extends javax.swing.JDialog {

    /**
     Creates new form SendNisira
     */
    ArrayList<Object[]> data;
    String idtareos;
    Window w;
    boolean NOERROR = true;
    JCallback callbackFrame;

    public SendNisira(java.awt.Frame parent, boolean modal, String idtareos, JCallback callbackFrame) {
        super(parent, modal);
        initComponents();
        this.idtareos = idtareos;
        this.callbackFrame = callbackFrame;
        this.w = parent;
        gettin_data();
    }

    private void gettin_data() {
        tabla.initHttp("0",
                "idtareo, idusuario, documento, idestado, fecha, semana, idplanilla, idturno,estado",
                "idtareo, idusuario, documento, idestado, fecha, semana, idplanilla, idturno,estado",
                "Stringx4,DateSQLx1,Integerx1,Stringx2,Integerx1",
                gettin_pages.api_get() + "exec GetListTareoFilter '" + this.idtareos + "';");
        tabla.GetDatosHTTP();
        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatSendNisira());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        jPanel1 = new javax.swing.JPanel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        btnIniciar = new kevin.component.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Envio de Tareo a Nisira");
        setUndecorated(true);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel1.setBackground(defaults.colorPrimaryDark);

        etiqueta1.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta1.setText("Envio de Tareos a ERP Externo");
        etiqueta1.setESITALIC(true);
        etiqueta1.setFONT_SIZE(20.0F);

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon1.setICO_color(java.awt.Color.white);
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });

        btnIniciar.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciar.setForeground(new java.awt.Color(102, 102, 102));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        dispose();
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed

        for (int i = 0; i < tabla.getRowCount(); i++) {

            Object[] RESULT = jsonmethods.GetData2(w,
                    gettin_pages.api_get()
                    + ExecHTTP.parseQL("exec SetTareoExport  ",
                            new Object[]{tabla.getValueAt(i, 0).toString(),
                                         tabla.getValueAt(i, 4).toString(),
                                         tabla.getValueAt(i, 1).toString(),
                                         jkeys.IDDATABASE,
                                         tabla.getValueAt(i, 6).toString(),
                                         tabla.getValueAt(i, 7).toString(),
                                         RunMain.INFO_HOST}
                    ),
                    "rpta,titulo,contenido,clase",
                    "String,String,String,String")
                    .get(0);

            if (!RESULT[0].toString().equals("Succesfull")) {
                NOERROR = false;
                Toast.makeText((JFrame) w, "HA OCURRIDO UN ERROR, SE DETENDRA EL ENVIO DE TAREOS", Style.ERROR).display();
                JOptionPane.showMessageDialog(w, RESULT[2], "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
                break;
            } else if (RESULT[0].toString().equals("Succesfull")) {
                NOERROR = true;
                Toast.makeText((JFrame) w, "TAREO EXPORTADO CORRECTAMENTE", Style.SUCCESS).display();
                tabla.setValueAt(1, i, 8);
            }

        }

        if (NOERROR) {
            btnIniciar.setVisible(false);
            JOptionPane.showMessageDialog(w, "SE EXPORTARON TODOS LOS TAREOS CORRECTAMENTE", "Cambio Realizado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(w, "OCURRIO UN PROBLEMA, SE DETUVO EL PROCESO DE ENVIO DE TAREOS", "Mensaje del Sistema", JOptionPane.WARNING_MESSAGE);
        }

        if (callbackFrame != null) {
            dispose();
            callbackFrame.action();
        }

    }//GEN-LAST:event_btnIniciarActionPerformed

    class FormatSendNisira extends DefaultTableCellRenderer {

        public FormatSendNisira() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 8:
                    if (value.toString().equals("1")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/done24Green.png")));
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.WHITE);
                    } else if (value.toString().equals("0")) {
                        setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/person_stop.png")));
                        setBackground(Color.WHITE);
                        setForeground(MaterialColor.WHITE);
                    }
                    break;
                default:
                    setIcon(null);
                    setBackground(Color.WHITE);
                    setForeground(MaterialColor.GREY_800);
            }

            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            return this;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnIniciar;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private kevin.component.label.Etiqueta etiqueta1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private kevin.component.tabla.TablaSmart tabla;
    // End of variables declaration//GEN-END:variables
}
