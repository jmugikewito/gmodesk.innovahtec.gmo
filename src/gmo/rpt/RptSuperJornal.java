package gmo.rpt;

import app.RunMain;
import gmo.methods.jmethods;
import gmo.utils.jkeys;
import gmo.utils.jvalues;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.voids.JMethods;
import jmugi.voids.PrintMethods;
import jmugi.voids.gmoEncript;
import jmugi.voids.gmoEncript2022;
import kevin.component.defaults;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class RptSuperJornal extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    String FECHA_DATE;
    String IDCULTIVO = "";
    SmartLoader load;

    public RptSuperJornal(Window w) {
        this.Frame = w;
        initComponents();
        setSize(980, 720);

        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });
        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });
        jmethods.cargarPlanillas(cboPlanillas, " 'u','8' ", jvalues.USUARIO.getFirma().length() > 0 ? gmoEncript2022.encriptar(JMethods.splitStringComa(jvalues.USUARIO.getFirma())) : "''");
        gettin_data();
    }

    private void gettin_data() {
 /*
        tabla.initHttp("",
                "fecha,idtrabajador,nombres,total",
                "fecha,idtrabajador,nombres,total",
                "DateSQLx1,Stringx2,Doublex1",
                RunMain.gettin_pages.api_get() + ExecHTTP.parseQL(
                "exec GetRptSobreJornal ", jkeys.IDDATABASE, jkeys.IDEMPRESA, inicio.toStringDate(), fin.toStringDate(), Integer.parseInt(spnHora.getValue().toString()), cboPlanillas.getIditem().toString()
        )
        );
        tabla.GetDatosHTTP2022();
        JMethods.updateInternalJTable(this, tabla);
        DATA = tabla.getDATA();
         */
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos del Personal Observado",
                "Se estan Descargando el personal que se ha suspendido temporalmente...",
                (Window frame) -> {                    
                    tabla.loadApiDataSmart(
                                        "api/desk/gestion-humana/rpt-sobre-jornal",
                                        "iddatabase,idempresa,inicio,fin,par,idplanilla",
                                        jkeys.IDDATABASE, jkeys.IDEMPRESA, inicio.toStringDate(), fin.toStringDate(), Integer.parseInt(spnHora.getValue().toString()), cboPlanillas.getIditem().toString()
                                );                   
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        
        JMethods.settingGlassPane((JFrame) Frame, load, defaults.colorPrimary, 0.5f);
        load = null;
        JMethods.updateInternalJTable(this, tabla); 
         
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        etiqueta1 = new kevin.component.label.Etiqueta();
        etiqueta2 = new kevin.component.label.Etiqueta();
        etiqueta3 = new kevin.component.label.Etiqueta();
        etiqueta4 = new kevin.component.label.Etiqueta();
        etiqueta5 = new kevin.component.label.Etiqueta();
        etiqueta6 = new kevin.component.label.Etiqueta();
        etiqueta7 = new kevin.component.label.Etiqueta();
        etiqueta8 = new kevin.component.label.Etiqueta();
        etiqueta9 = new kevin.component.label.Etiqueta();
        etiqueta10 = new kevin.component.label.Etiqueta();
        etiqueta11 = new kevin.component.label.Etiqueta();
        etiqueta12 = new kevin.component.label.Etiqueta();
        etiqueta13 = new kevin.component.label.Etiqueta();
        etiqueta14 = new kevin.component.label.Etiqueta();
        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        panelData = new kevin.component.panel.Panel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        etiqueta15 = new kevin.component.label.Etiqueta();
        spnHora = new javax.swing.JSpinner();
        etiqueta16 = new kevin.component.label.Etiqueta();
        inicio = new kevin.component.date.MaterialDateChooser();
        etiqueta17 = new kevin.component.label.Etiqueta();
        fin = new kevin.component.date.MaterialDateChooser();
        cboPlanillas = new kevin.component.combobox.ComboBox();
        lblPlanilla = new javax.swing.JLabel();
        panelChart = new kevin.component.panel.Panel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 7, 2, 2));

        etiqueta1.setBackground(defaults.colorPrimaryDark);
        etiqueta1.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta1.setText("Lunes");
        etiqueta1.setOpaque(true);
        jPanel2.add(etiqueta1);

        etiqueta2.setBackground(defaults.colorPrimaryDark);
        etiqueta2.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta2.setText("Martes");
        etiqueta2.setOpaque(true);
        jPanel2.add(etiqueta2);

        etiqueta3.setBackground(defaults.colorPrimaryDark);
        etiqueta3.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta3.setText("Miercoles");
        etiqueta3.setOpaque(true);
        jPanel2.add(etiqueta3);

        etiqueta4.setBackground(defaults.colorPrimaryDark);
        etiqueta4.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta4.setText("Jueves");
        etiqueta4.setOpaque(true);
        jPanel2.add(etiqueta4);

        etiqueta5.setBackground(defaults.colorPrimaryDark);
        etiqueta5.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta5.setText("Viernes");
        etiqueta5.setOpaque(true);
        jPanel2.add(etiqueta5);

        etiqueta6.setBackground(defaults.colorPrimaryDark);
        etiqueta6.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta6.setText("Sabado");
        etiqueta6.setOpaque(true);
        jPanel2.add(etiqueta6);

        etiqueta7.setBackground(defaults.colorPrimaryDark);
        etiqueta7.setForeground(new java.awt.Color(255, 255, 255));
        etiqueta7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta7.setText("Domingo");
        etiqueta7.setOpaque(true);
        jPanel2.add(etiqueta7);

        etiqueta8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta8.setText("0");
        jPanel2.add(etiqueta8);

        etiqueta9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta9.setText("0");
        jPanel2.add(etiqueta9);

        etiqueta10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta10.setText("0");
        jPanel2.add(etiqueta10);

        etiqueta11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta11.setText("0");
        jPanel2.add(etiqueta11);

        etiqueta12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta12.setText("0");
        jPanel2.add(etiqueta12);

        etiqueta13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta13.setText("0");
        jPanel2.add(etiqueta13);

        etiqueta14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiqueta14.setText("0");
        jPanel2.add(etiqueta14);

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Reporte de Sobre Jornales");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.CardLayout());

        panelData.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        etiqueta15.setText("Jornal Minimo");

        spnHora.setFont(defaults.FUENTE);
        spnHora.setModel(new javax.swing.SpinnerNumberModel(10, 10, 32, 1));

        etiqueta16.setText("Inicio");

        etiqueta17.setText("Fin");

        cboPlanillas.setPreferredSize(new java.awt.Dimension(240, 34));

        lblPlanilla.setText("  Planilla");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(etiqueta16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiqueta17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPlanilla)
                        .addGap(5, 5, 5)
                        .addComponent(cboPlanillas, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(etiqueta15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnHora, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 163, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(etiqueta16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiqueta17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(lblPlanilla))
                        .addComponent(cboPlanillas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(etiqueta15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(spnHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelData.add(jPanel3, java.awt.BorderLayout.CENTER);

        contenedor.add(panelData, "cardData");

        panelChart.setLayout(new java.awt.BorderLayout());

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        panelChart.add(jPanel4, java.awt.BorderLayout.CENTER);

        contenedor.add(panelChart, "cardChart");

        jPanel1.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setCHART(false);
        toolbar1.setELIMINAR(false);
        toolbar1.setEXCEL(true);
        toolbar1.setRECARGAR(true);
        jPanel1.add(toolbar1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.combobox.ComboBox cboPlanillas;
    private javax.swing.JPanel contenedor;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta10;
    private kevin.component.label.Etiqueta etiqueta11;
    private kevin.component.label.Etiqueta etiqueta12;
    private kevin.component.label.Etiqueta etiqueta13;
    private kevin.component.label.Etiqueta etiqueta14;
    private kevin.component.label.Etiqueta etiqueta15;
    private kevin.component.label.Etiqueta etiqueta16;
    private kevin.component.label.Etiqueta etiqueta17;
    private kevin.component.label.Etiqueta etiqueta2;
    private kevin.component.label.Etiqueta etiqueta3;
    private kevin.component.label.Etiqueta etiqueta4;
    private kevin.component.label.Etiqueta etiqueta5;
    private kevin.component.label.Etiqueta etiqueta6;
    private kevin.component.label.Etiqueta etiqueta7;
    private kevin.component.label.Etiqueta etiqueta8;
    private kevin.component.label.Etiqueta etiqueta9;
    private kevin.component.date.MaterialDateChooser fin;
    private kevin.component.date.MaterialDateChooser inicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPlanilla;
    private kevin.component.panel.Panel panelChart;
    private kevin.component.panel.Panel panelData;
    private javax.swing.JSpinner spnHora;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
