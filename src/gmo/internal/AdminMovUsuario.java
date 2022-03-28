package gmo.internal;

import app.RunMain;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import gmo.utils.jkeys;
import java.awt.CardLayout;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import jmugi.voids.JMethods;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;
import utils.jsonmethods;

/**

 @author Asus
 */
public class AdminMovUsuario extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    public String TIPOENTRADA, IDMOTIVO;
    public CardLayout CARDLAYOUT;
    String IDUSUARIO1, IDUSUARIO2;
    public ArrayList<Object[]> LISTA_DATA;

    SmartLoader load;
    public int FILA = 0;
    public int EDITNUEVO = 0;//0 para Nuevo y 1 para EDITAR

    public AdminMovUsuario(Window w) {
        initComponents();
        this.Frame = w;
        initComponents();
        setSize(640, 720);
        CARDLAYOUT = (CardLayout) contenedor.getLayout();
        CARDLAYOUT.show(contenedor, "cardGeneral");

        cboUser1.setIsDataEncript(true);
        cboUsuario.setIsDataEncript(true);

        cboUser1.GetDataSQL(RunMain.CONECT.con,
                ExecHTTP.parseQuery("SELECT idusuario, nombres\n"
                        + "FROM usuario\n"
                        + "WHERE idtipousuario IN (SELECT idtipousuario FROM tipousuario WHERE idtipousuario LIKE '2767%' OR idtipousuario LIKE 'rpu7s%' OR idtipousuario LIKE 'rp057s%' OR idtipousuario LIKE ' x zmrq%' OR idtipousuario LIKE ' r3rq%')\n"
                        + "  AND activo = ?1 order by nombres", false, 1),
                2);
        cboUsuario.GetDataSQL(RunMain.CONECT.con,
                ExecHTTP.parseQuery("SELECT idusuario, nombres\n"
                        + "FROM usuario\n"
                        + "WHERE idtipousuario IN (SELECT idtipousuario FROM tipousuario WHERE idtipousuario LIKE '2767%' OR idtipousuario LIKE 'rpu7s%' OR idtipousuario LIKE 'rp057s%' OR idtipousuario LIKE ' x zmrq%' OR idtipousuario LIKE ' r3rq%')\n"
                        + "  AND activo = ?1  order by nombres;", false, 1),
                2);

        aplyEvents();

    }

    public void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos...",
                "Se estan Descargando las Asignaciones de Usuarios...",
                (Window frame) -> {
                    tabla.initHttp("140, 180, 200, 80",
                            "usuario,area,observaciones,activo",
                            "usuario,area,observaciones,activo",
                            "Stringx3,Integerx1",
                            gettin_pages.api_get() + "exec GetDataMovUsuario '" + jkeys.IDDATABASE + "','" + jkeys.IDEMPRESA + "','" + IDUSUARIO1 + "';"
                    );
                    tabla.GetDatosHTTP2022();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, color.MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);
        DATA = tabla.getDATA();
        tabla.revalidate();
        pack();
    }

    public void applyEditarAgregar() {
        IDUSUARIO2 = cboUsers2.getCellRenderer().getLabel().getText();

        ExecHTTP.ExecPostProcedure(Frame,
                gettin_pages.api_set(),
                new String[]{"iddatabase2", "query"},
                new Object[]{
                    jkeys.IDDATABASE2,
                    ExecHTTP.parseQL("exec SetMovUsuario ",
                            new Object[]{
                                jkeys.IDDATABASE,
                                jkeys.IDEMPRESA,
                                IDUSUARIO1,
                                JMethods.ceroEspaciosMa(IDUSUARIO2),
                                edtObservaciones.getText()
                            }
                    )
                },
                () -> {//ACTION DONE
                    gettin_data();
                    CARDLAYOUT.show(contenedor, "cardData");
                },
                () -> {//ACTION WARN
                });
    }

    private void aplyEvents() {
        toolbar.setAGREGAR_CALLBACK(() -> {
            CARDLAYOUT.show(contenedor, "cardFilter");
            cboUser1.setSelectedIndex(cboUsuario.getSelectedIndex());
            cboUsers2.deSelected();
            edtObservaciones.setText("");
        });
        toolbar.setRECARGAR_CALLBACK(this::gettin_data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        toolbar = new kevin.component.toolbar.Toolbar();
        jPanel3 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        cardData = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        cardFilter = new javax.swing.JPanel();
        panel1 = new kevin.component.panel.Panel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        edtObservaciones = new javax.swing.JTextArea();
        cboUser1 = new kevin.component.combobox.ComboBox();
        edtDescArea = new kevin.component.edittext.EditText();
        btnClose = new kevin.component.button.ButtonMaterialIcon();
        btnGuardar1 = new kevin.component.button.Button();
        cboUsers2 = new kevin.component.combobox.ComboCheckBox();
        cboUsuario = new kevin.component.combobox.ComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Administracion de Usuarios a Cargo");
        setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        toolbar.setAGREGAR(true);
        toolbar.setCHART(false);
        toolbar.setEXCEL(false);
        toolbar.setFILTRAR(false);
        toolbar.setIMPRIMIR(false);
        toolbar.setRECARGAR(true);
        toolbar.setMaximumSize(new java.awt.Dimension(42, 240));
        toolbar.setMinimumSize(new java.awt.Dimension(42, 240));
        toolbar.setPreferredSize(new java.awt.Dimension(42, 240));
        jPanel1.add(toolbar, java.awt.BorderLayout.WEST);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.CardLayout());

        cardData.setLayout(new java.awt.BorderLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setIsAutomaticResize(false);
        jScrollPane2.setViewportView(tabla);

        cardData.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        contenedor.add(cardData, "cardData");

        cardFilter.setOpaque(false);
        cardFilter.setLayout(new java.awt.GridBagLayout());

        panel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setOpaque(false);

        jLabel2.setText("Usuario Principal");

        jLabel3.setText("Usuario Secundario");

        edtObservaciones.setColumns(20);
        edtObservaciones.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        edtObservaciones.setLineWrap(true);
        edtObservaciones.setRows(5);
        jScrollPane4.setViewportView(edtObservaciones);

        cboUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUser1ActionPerformed(evt);
            }
        });

        edtDescArea.setLabel("Descripcion Area");

        btnClose.setText("buttonMaterialIcon1");
        btnClose.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        btnClose.setICO_color(new java.awt.Color(204, 204, 204));
        btnClose.setICO_size(22.0F);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnGuardar1.setText("Guardar");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                    .addComponent(edtDescArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboUsers2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(cboUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboUsers2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtDescArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 288;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        cardFilter.add(panel1, gridBagConstraints);

        contenedor.add(cardFilter, "cardFilter");

        cboUsuario.setIsDataEncript(true);
        cboUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(474, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(558, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUser1ActionPerformed
//        IDUSUARIO1 = jvalues.LIST_USUARIOS.get(cboUser1.getSelectedIndex()).getIdusuario();
        IDUSUARIO1 = cboUser1.getIditem().toString();
        ArrayList<Object[]> LISTA = jsonmethods.GetData2(null,
                gettin_pages.api_get()
                + ExecHTTP.parseQL("exec GetDataMovUsuario ", new Object[]{jkeys.IDDATABASE, jkeys.IDEMPRESA, IDUSUARIO1, 1}),
                "idusuario",
                new jsonmethods.COLUMN[]{jsonmethods.COLUMN.String}
        );
        cboUsers2.clear();
        for (int i = 0; i < LISTA.size(); i++)
            cboUsers2.addItem(LISTA.get(i)[0].toString(), false);

    }//GEN-LAST:event_cboUser1ActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        CARDLAYOUT.show(contenedor, "cardData");
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        applyEditarAgregar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void cboUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuarioActionPerformed
        IDUSUARIO1 = cboUsuario.getIditem().toString();
        gettin_data();
    }//GEN-LAST:event_cboUsuarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.ButtonMaterialIcon btnClose;
    private kevin.component.button.Button btnGuardar1;
    private javax.swing.JPanel cardData;
    private javax.swing.JPanel cardFilter;
    private kevin.component.combobox.ComboBox cboUser1;
    private kevin.component.combobox.ComboCheckBox cboUsers2;
    private kevin.component.combobox.ComboBox cboUsuario;
    private javax.swing.JPanel contenedor;
    private kevin.component.edittext.EditText edtDescArea;
    private javax.swing.JTextArea edtObservaciones;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private kevin.component.panel.Panel panel1;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar;
    // End of variables declaration//GEN-END:variables
}
