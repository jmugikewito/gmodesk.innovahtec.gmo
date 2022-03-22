package gmo.rpt;

import acore.principalvalues;
import app.RunMain;
import color.MaterialColor;
import gmo.dialog.BuscarDialog;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import jmugi.voids.DateTimeUtil;
import jmugi.voids.JMethods;
import jmugi.voids.JOptionPane_methods;

/**

 @author Asus
 */
public class RptHorasExtraBono extends javax.swing.JInternalFrame {

    int TIPO = 0, CONCONSUMIDOR = 0;
    public Object[][] DATA;
    Window Frame;
    public CardLayout CARDLAYOUT;
    String FECHA_DATE1 = "", FECHA_DATE2 = "", IDUSER = "", TYPEREPORT = "ALC", IDACTIVIDAD = "", IDLABOR = "", IDCONSUMIDOR = "", OBSERVACIONES = "";

    String titles;
    int tams[];
    Class types[];
    String _tipes;

    BuscarDialog buscarLaborActividad;

    public RptHorasExtraBono(Window w) {
        this.Frame = w;
        initComponents();

        chooserFecha1.setDate(new Date());
        FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        chooserFecha1.setCallback(() -> {
            FECHA_DATE1 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha1.getDate());
        });
        tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        chooserFecha2.setDate(new Date());
        FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        chooserFecha2.setCallback(() -> {
            FECHA_DATE2 = DateTimeUtil.getDate_yyyyMMdd(chooserFecha2.getDate());
        });

        toolbar1.setRECARGAR_CALLBACK(this::gettin_data);
        toolbar1.setEXCEL_CALLBACK(() -> {
            tabla.exportExcel((JFrame) Frame, getTitle());
        });
        toolbar1.setFILTRAR_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardFilter");
        });

        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardFilter");

        comboBox1.setSelectedIndex(0);
    }

    public void gettin_data() {

        if (chkConsumidor.isSelected()) {// CON CONSUMIDOR
            if (chkFechas.isSelected()) {// CON FECHA
                tams = new int[]{100, 100, 240, 100, 240, 80, 80, 80, 80, 80, 160};
                types = new Class[]{String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class};
                titles = "fecha,dni,nombres,idconsumidor,consumidor,horas,rend,avance,bono,motivo,observaciones";
                _tipes = "DateSQLx1,Stringx4,Doublex4,Stringx2";
            } else {// SIN FECHA
                tams = new int[]{100, 240, 100, 240, 80, 80, 80, 80, 160};
                titles = "dni,nombres,idconsumidor,consumidor,horas,rend,avance,bono,observaciones";
                types = new Class[]{String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class};
                _tipes = "Stringx4,Doublex4,Stringx1";
            }
        } else {// SIN CONSUMIDOR
            if (chkFechas.isSelected()) {//CON FECHA
                if (IDACTIVIDAD.equals("JAVA_CLAMSHELL")) {
                    tams = new int[]{100, 100, 240, 160, 80, 80, 80, 80};
                    titles = "fecha,dni,nombres,labor,horas,rend,avance,bono";
                    types = new Class[]{String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class};
                    _tipes = "DateSQLx1,Stringx3,Doublex4";
                } else {
                    tams = new int[]{100, 100, 240, 80, 80, 80, 80};
                    titles = "fecha,dni,nombres,horas,rend,avance,bono";
                    types = new Class[]{String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class};
                    _tipes = "DateSQLx1,Stringx2,Doublex4";
                }

            } else {//SIN FECHA
                tams = new int[]{100, 240, 80, 80, 80, 80};
                titles = "fecha,dni,nombres,horas,rend,avance,bono";
                types = new Class[]{String.class, String.class, Double.class, Double.class, Double.class, Double.class};
                _tipes = "DateSQLx1,Stringx2,Doublex4";
            }
        }
        tabla.setTYPES(types);
        tabla.initHttp("",
                titles,
                titles,
                _tipes,
                RunMain.gettin_pages.api_get() + "EXEC GetRptExtraBono '" + jkeys.IDDATABASE + "', '" + jkeys.IDEMPRESA + "',"
                + " '" + FECHA_DATE1 + "', '" + FECHA_DATE2 + "', '" + IDACTIVIDAD + "', '" + IDLABOR + "', "
                + edtMeta.getText() + ", "
                + edtCalculo.getText() + ","
                + (chkConsumidor.isSelected() ? 1 : 0) + ","
                + (chkFechas.isSelected() ? 1 : 0) + ",'"
                + (chkObservacioens.isSelected() ? OBSERVACIONES : "") + "',"
                + "1;"
        );

        tabla.GetDatosHTTP();
        tabla.setDefaultRenderer(Object.class, new FormatExtraBono());
        DATA = tabla.getDATA();
        CARDLAYOUT.show(contenedor, "cardData");
        JMethods.updateInternalJTable(this, tabla);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        smpExportar = new javax.swing.JMenuItem();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        toolbar1 = new kevin.component.toolbar.Toolbar();
        contenedor = new javax.swing.JPanel();
        cardData = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        cardFilter = new javax.swing.JPanel();
        panel1 = new kevin.component.panel.Panel();
        jPanel2 = new javax.swing.JPanel();
        chooserFecha1 = new kevin.component.date.MaterialDateChooser();
        etiqueta1 = new kevin.component.label.Etiqueta();
        chooserFecha2 = new kevin.component.date.MaterialDateChooser();
        etiqueta2 = new kevin.component.label.Etiqueta();
        btnLabor = new kevin.component.button.Button();
        etLabor = new kevin.component.label.Etiqueta();
        jSeparator1 = new javax.swing.JSeparator();
        radExtras = new kevin.component.radiobox.RadioBox();
        radBonos = new kevin.component.radiobox.RadioBox();
        chkConsumidor = new kevin.component.checkbox.CheckBox();
        chkFechas = new kevin.component.checkbox.CheckBox();
        chkObservacioens = new kevin.component.checkbox.CheckBox();
        comboBox1 = new kevin.component.combobox.ComboBox();
        edtMeta = new kevin.component.edittext.EditText();
        edtCalculo = new kevin.component.edittext.EditText();
        chkContrata = new kevin.component.checkbox.CheckBox();
        btnContrata = new kevin.component.button.Button();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();

        smpExportar.setText("jMenuItem1");
        smpExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smpExportarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(smpExportar);

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Reporte de Extras y Bonos (en ERP Externo)");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        toolbar1.setEXCEL(true);
        toolbar1.setFILTRAR(true);
        toolbar1.setRECARGAR(true);
        toolbar1.setMaximumSize(new java.awt.Dimension(42, 240));
        toolbar1.setMinimumSize(new java.awt.Dimension(42, 240));
        toolbar1.setPreferredSize(new java.awt.Dimension(42, 240));
        jPanel4.add(toolbar1, java.awt.BorderLayout.WEST);

        contenedor.setLayout(new java.awt.CardLayout());

        cardData.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout cardDataLayout = new javax.swing.GroupLayout(cardData);
        cardData.setLayout(cardDataLayout);
        cardDataLayout.setHorizontalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardDataLayout.setVerticalGroup(
            cardDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(cardData, "cardData");

        cardFilter.setBackground(new java.awt.Color(255, 255, 255));
        cardFilter.setLayout(new java.awt.GridBagLayout());

        jPanel2.setOpaque(false);

        chooserFecha1.setWeekOfYearVisible(false);

        etiqueta1.setText("Inicio");

        chooserFecha2.setWeekOfYearVisible(false);

        etiqueta2.setText("Fin");

        btnLabor.setText("Labor");
        btnLabor.setEnabled(false);
        btnLabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaborActionPerformed(evt);
            }
        });

        etLabor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etLabor.setText("");
        etLabor.setEnabled(false);

        buttonGroup1.add(radExtras);
        radExtras.setText("Extras");
        radExtras.setEnabled(false);

        buttonGroup1.add(radBonos);
        radBonos.setSelected(true);
        radBonos.setText("Bonos");

        chkConsumidor.setText("Incluye Consumidores");

        chkFechas.setSelected(true);
        chkFechas.setText("Incluye Fechas");
        chkFechas.setEnabled(false);

        chkObservacioens.setSelected(true);
        chkObservacioens.setText("Observaciones");

        comboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arandano - Cosecha", "Arandano - Cosecha Rend. Granel", "Arandano - Cosecha Rend. Clamshell", "Arandano - Cosecha Granel/Clamshell", "Uva - Poda", "Uva - Deshoje" }));
        comboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox1ActionPerformed(evt);
            }
        });

        edtMeta.setText("0");
        edtMeta.setLabel("Ingresar Meta");
        edtMeta.setModo(kevin.component.edittext.EditText.MODO.NUMEROS);

        edtCalculo.setText("0");
        edtCalculo.setLabel("Ingresar Calculo");
        edtCalculo.setLeft(java.lang.Boolean.TRUE);
        edtCalculo.setModo(kevin.component.edittext.EditText.MODO.NUMEROS);

        chkContrata.setText("Contrata");
        chkContrata.setEnabled(false);

        btnContrata.setText("Personal Contrata");
        btnContrata.setEnabled(false);

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.DONE);
        buttonMaterialIcon1.setICO_size(64.0F);
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(radExtras, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(radBonos, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(edtMeta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(edtCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(chkConsumidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkFechas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chkObservacioens, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(etLabor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(etiqueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chooserFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(chooserFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkContrata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnContrata, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiqueta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooserFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooserFecha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiqueta2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etLabor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkConsumidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkFechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkObservacioens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radExtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radBonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtMeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnContrata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkContrata, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 400, 10);
        cardFilter.add(panel1, gridBagConstraints);

        contenedor.add(cardFilter, "cardFilter");

        jPanel4.add(contenedor, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void smpExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smpExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smpExportarActionPerformed

    private void btnLaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaborActionPerformed
        JDialog.setDefaultLookAndFeelDecorated(false);
        JMethods.settingGlassPane((JFrame) Frame, buscarLaborActividad, principalvalues.colorAccent, 0.6f);
        if (buscarLaborActividad.DATA_SELECT != null) {
            IDLABOR = buscarLaborActividad.DATA_SELECT[0].toString();
            etLabor.setText(buscarLaborActividad.DATA_SELECT[1].toString());
            IDACTIVIDAD = buscarLaborActividad.DATA_SELECT[2].toString();
//            etLabor.setText(buscarLaborActividad.DATA_SELECT[3].toString());
            buscarLaborActividad.DATA_SELECT = null;
        }
        JDialog.setDefaultLookAndFeelDecorated(true);
    }//GEN-LAST:event_btnLaborActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        JOptionPane_methods.MostrarConfirmacion(Frame, (Window frame) -> {
            gettin_data();
        });
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void comboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox1ActionPerformed
        switch (comboBox1.getSelectedIndex()) {
            case 0:
                edtMeta.setText(12 + "");
                edtCalculo.setText(3.02 + "");
                btnContrata.setEnabled(false);
                chkContrata.setEnabled(false);
                IDACTIVIDAD = "006";
                IDLABOR = "002";
                OBSERVACIONES = "";
                break;
            case 1:
                edtMeta.setText(12 + "");
                edtCalculo.setText(3.02 + "");
                btnContrata.setEnabled(false);
                chkContrata.setEnabled(false);
                IDACTIVIDAD = "R02";
                IDLABOR = "001";
                OBSERVACIONES = "";
                break;
            case 2:
                edtMeta.setText(12 + "");
                edtCalculo.setText(3.024166 + "");
                btnContrata.setEnabled(false);
                chkContrata.setEnabled(false);
                IDACTIVIDAD = "R02";
                IDLABOR = "002";
                OBSERVACIONES = "";
            case 3:
                edtMeta.setText(17 + "");
                edtCalculo.setText(2.000000 + "");
                btnContrata.setEnabled(false);
                chkContrata.setEnabled(false);
                IDACTIVIDAD = "JAVA_CLAMSHELL";
                IDLABOR = "002";
                OBSERVACIONES = "";
                break;
            case 5:
                edtMeta.setText(238 + "");
                edtCalculo.setText(0.23 + "");
                btnContrata.setEnabled(true);
                chkContrata.setEnabled(true);
                IDACTIVIDAD = "010";
                IDLABOR = "005";
                OBSERVACIONES = "poda";
                break;
            case 6:
                edtMeta.setText(272 + "");
                edtCalculo.setText(0.23 + "");
                btnContrata.setEnabled(true);
                chkContrata.setEnabled(true);
                IDACTIVIDAD = "010";
                IDLABOR = "005";
                OBSERVACIONES = "deshoje";
                break;
        }
    }//GEN-LAST:event_comboBox1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
    }//GEN-LAST:event_tablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnContrata;
    private kevin.component.button.Button btnLabor;
    private javax.swing.ButtonGroup buttonGroup1;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private javax.swing.JPanel cardData;
    private javax.swing.JPanel cardFilter;
    private kevin.component.checkbox.CheckBox chkConsumidor;
    private kevin.component.checkbox.CheckBox chkContrata;
    private kevin.component.checkbox.CheckBox chkFechas;
    private kevin.component.checkbox.CheckBox chkObservacioens;
    private kevin.component.date.MaterialDateChooser chooserFecha1;
    private kevin.component.date.MaterialDateChooser chooserFecha2;
    private kevin.component.combobox.ComboBox comboBox1;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edtCalculo;
    private kevin.component.edittext.EditText edtMeta;
    private kevin.component.label.Etiqueta etLabor;
    private kevin.component.label.Etiqueta etiqueta1;
    private kevin.component.label.Etiqueta etiqueta2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private kevin.component.panel.Panel panel1;
    private kevin.component.radiobox.RadioBox radBonos;
    private kevin.component.radiobox.RadioBox radExtras;
    private javax.swing.JMenuItem smpExportar;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables

    public class FormatExtraBono extends DefaultTableCellRenderer {

        public FormatExtraBono() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            setEnabled(table == null || table.isEnabled());
            switch (column) {
                case 5:
                    if (value.toString().equals("0.00")) {
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                        setBackground(MaterialColor.PINK_400);
                        setForeground(Color.WHITE);
                    }
                    break;
                default:
                    setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    setBackground(Color.WHITE);
                    setForeground(Color.BLACK);
                    break;
            }
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            return this;
        }
    }
}
