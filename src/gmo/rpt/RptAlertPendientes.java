package gmo.rpt;

import app.RunMain;
import gmo.utils.jkeys;
import iconfont.MATERIALICON;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import jmugi.component.MaterialColor;
import jmugi.voids.JMethods;
import kevin.component.dialog.SmartLoader;

/**

 @author Asus
 */
public class RptAlertPendientes extends javax.swing.JInternalFrame {

    /**
     Creates new form RptAlertPendientes
     */
    JFrame FRAME;
    SmartLoader load;

    public RptAlertPendientes(JFrame frame) {
        initComponents();
        this.FRAME = frame;
        chooserFecha1.setDate(new Date());
        chooserFecha2.setDate(new Date());
        chooserFecha1.setCallback(() -> {
            chooserFecha2.setDate(chooserFecha1.getDate());
            gettin_data();
        });
        gettin_data();


    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) FRAME, true,
                "Descargando Datos de Nisira",
                "Se estan Descargando los Tareos de la Fecha, para Generar Asistencia",
                (Window frame) -> {
                    tabla.initHttp("0",
                            "idtareo,fecha,idusuario,usuario,planilla,numero,turno,semana,periodo,e_jornal,e_rendimiento,estado",
                            "idtareo,fecha,idusuario,usuario,planilla,numero,turno,semana,periodo,e_jornal,e_rendimiento,estado",
                            "Stringx1,DateSQLx1,Stringx5,Integerx1,Stringx3,Integerx1",
                            RunMain.gettin_pages.api_get() + "exec GetRptTareosOtherERP "
                            + "'" + jkeys.IDEMPRESA + "',"
                            + "'" + chooserFecha1.toStringDate() + "',"
                            + "'" + chooserFecha2.toStringDate() + "',"
                            + "" + (chkGeneroAsistencia.isSelected() ? 1 : 0) + ";"
                    );
                    tabla.GetDatosHTTP2022();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) FRAME, load, color.MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        tabla.setDefaultRenderer(Object.class, (TableCellRenderer) new FormatPENisira());
        JMethods.updateInternalJTable(this, tabla);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new kevin.component.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        etiqueta1 = new kevin.component.label.Etiqueta();
        btnConsultar = new kevin.component.button.Button();
        chkGeneroAsistencia = new kevin.component.checkbox.CheckBox();
        chooserFecha2 = new kevin.component.date.MaterialDateChooser();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alerta de Pendientes en Nisira");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jScrollPane1.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        etiqueta1.setText("Fechas");

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        chkGeneroAsistencia.setText("Genero Asistencia");

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setToolTipText("Revisar Problemas en SubTareos");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.WARNING);
        buttonMaterialIcon1.setICO_color(new java.awt.Color(255, 102, 102));
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooserFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                        .addComponent(chkGeneroAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chooserFecha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkGeneroAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserFecha2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        gettin_data();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnConsultar;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private kevin.component.checkbox.CheckBox chkGeneroAsistencia;
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private kevin.component.date.MaterialDateChooser chooserFecha2;
    private kevin.component.label.Etiqueta etiqueta1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private kevin.component.panel.Panel panel1;
    private kevin.component.tabla.TablaSmart tabla;
    // End of variables declaration//GEN-END:variables
}

class FormatPENisira extends DefaultTableCellRenderer {

    public FormatPENisira() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled());
        switch (column) {
            case 10:
                if (value.toString().equals("0")) {
                    setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.NOTIFICATIONS_ACTIVE, MaterialColor.RED_600, 18f));
                    setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    setBackground(MaterialColor.RED_200);
                    setForeground(MaterialColor.RED_200);
                } else if (value.toString().equals("1")) {
                    setIcon(MATERIALICON.paintBackgroundIcon(MATERIALICON.MATERIALICONIC.DONE, MaterialColor.TEAL_500, 18f));
                    setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    setBackground(MaterialColor.TEALA_700);
                    setForeground(MaterialColor.TEALA_700);
                }
                break;
            default:
                setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                setBackground(Color.WHITE);
                setIcon(null);
                setForeground(Color.BLACK);
                break;
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
}
