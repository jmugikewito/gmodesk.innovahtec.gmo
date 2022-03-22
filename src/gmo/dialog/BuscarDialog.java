package gmo.dialog;

import app.RunMain;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import java.awt.Frame;
import java.awt.Window;
import java.sql.Connection;
import jmugi.voids.JMethods;

/**

 @author jmugi
 */
public class BuscarDialog extends javax.swing.JDialog {

    public String TITLES;
    public String TAMS;
    public String CONSULTA;

    public Object[][] DATA;
    public Object[] DATA_SELECT;

    public Connection CON;

    public Window W;

    public String SMART_TITLES;
    public String SMART_TITLESJSON;
    public String SMART_TAMS;
    public String SMART_CONSULTA;
    public String SMART_TYPES;

    public BuscarDialog(Frame parent, boolean modal) {
        super(parent, modal);
        this.initComponents();
    }

    public BuscarDialog(Window W, boolean modal, Connection CON, String TITLES, String TAMS, String CONSULTA, boolean encript, boolean automatic) {
        super((Frame) W, modal);
        this.initComponents();
        this.TITLES = TITLES;
        this.TAMS = TAMS.trim().replace(" ", "");
        this.CONSULTA = CONSULTA;
        this.CON = CON;
        this.W = W;
        tabla.setIsDataEncript(encript);
        init(automatic);
    }

    public BuscarDialog(Window W, boolean modal, String SMART_TITLES, String SMART_TITLESJSON, String SMART_TAMS, String SMART_TYPES, String SMART_CONSULTA, boolean encript, boolean automatic) {
        super((Frame) W, modal);
        this.initComponents();
        this.W = W;
        this.SMART_TITLES = SMART_TITLES;
        this.SMART_TITLESJSON = SMART_TITLESJSON;
        this.SMART_TAMS = SMART_TAMS;
        this.SMART_CONSULTA = SMART_CONSULTA;
        this.SMART_TYPES = SMART_TYPES;
        tabla.setIsDataEncript(encript);
        initHttp(automatic);
    }

    private void tamsColumns(int[] TAMS) {
        int x = 0;
        for (int i = 0; i < TAMS.length; i++)
            x = x + TAMS[i];
        setSize(x + 60, 360);
    }

    private void init(boolean automatic) {
        tabla.setIsAutomaticResize(automatic);
        tabla.initSQL(
                //                "",
                automatic ? "" : TAMS,
                TITLES,
                CONSULTA);
        tabla.GetDatos(RunMain.CONECT.con);

//        tamsColumns(tabla.getTAMS());
    }

    private void initHttp(boolean automatic) {
        tabla.setIsAutomaticResize(automatic);
        tabla.initHttp("",
                //                SMART_TAMS,
                SMART_TITLES,
                SMART_TITLESJSON,
                SMART_TYPES,
                gettin_pages.api_get() + SMART_CONSULTA);
        tabla.GetDatosHTTP();

//        tamsColumns(tabla.getTAMS());
    }

    public void save_idoject() {
        DATA_SELECT = JMethods.getDataRow(tabla.getSelectedRow(), tabla);
    }

    public void Encripted() {
        tabla.setIsDataEncript(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new kevin.component.button.Button();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(colorPrimaryDark);

        jLabel1.setFont(new java.awt.Font("Arial", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon1.setICO_color(null);
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
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonMaterialIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.save_idoject();
        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private kevin.component.button.Button btnGuardar;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private kevin.component.tabla.TablaSmart tabla;
    // End of variables declaration//GEN-END:variables
}
