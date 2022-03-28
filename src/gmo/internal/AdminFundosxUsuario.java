package gmo.internal;

import app.RunMain;
import gmo.dialog.BuscarDialog;
import java.awt.CardLayout;
import color.MaterialColor;
import static gmo.core.MainLite.*;
import java.awt.Window;
import java.util.ArrayList;
import jmugi.voids.JMethods;
import gmo.utils.jkeys;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import kevin.component.dialog.SmartLoader;
import utils.ExecHTTP;

/**

 @author Asus
 */
public class AdminFundosxUsuario extends javax.swing.JInternalFrame {

    public Object[][] DATA;
    public Window Frame;
    public String ID, IDFUNDO;
    public CardLayout CARDLAYOUT;

    public ArrayList<Object[]> LISTA_DATA;
    String DETALLEENVIAR;
    BuscarDialog buscarFundos;

    SmartLoader load;

    public AdminFundosxUsuario(Window w) {
        initComponents();
        setSize(720, 640);
        this.Frame = w;
        gettin_data();
        LISTA_DATA = new ArrayList();
        buscarFundos = new BuscarDialog(
                Frame,
                true,
                "idfundo,descripcion,tipo",
                "idfundo,descripcion,tipo",
                "120,360,100",
                "String,String,String",
                "SELECT\n"
                + "  idfundo,\n"
                + "  descripcion,\n"
                + "  CASE escampo\n"
                + "  WHEN 1\n"
                + "    THEN 'CAMPO'\n"
                + "  ELSE 'PACKING' END tipo\n"
                + "FROM fundo\n"
                + "WHERE activo = 1 AND iddatabase = '" + jkeys.IDDATABASE + "' AND idempresa = '" + jkeys.IDEMPRESA + "'",
                true, true
        );

        buscarFundos.setTitle("Seleccione el Fundo");
        toolbar1.setGUARDAR_CALLBACK(() -> {
            save();
        });
        toolbar1.setRECARGAR_CALLBACK(() -> {
            gettin_data();
        });
    }

    private void gettin_data() {
        JDialog.setDefaultLookAndFeelDecorated(false);
        load = new SmartLoader((java.awt.Frame) Frame, true,
                "Descargando Datos ...",
                "Se estan Descargando losFundos por Usuario",
                (Window frame) -> {
                    tabla.initHttp("",
                            "idusuario, nombres, idfundo, fundo",
                            "idusuario, nombres, idfundo, fundo",
                            "Stringx4",
                            gettin_pages.api_get() + ExecHTTP.parseQuery("exec GetListFundoUsuario ", false, jkeys.IDDATABASE, "1")
                    );
                    tabla.GetDatosHTTP2022();
                    load.dispose();
                    JDialog.setDefaultLookAndFeelDecorated(true);
                });
        JMethods.settingGlassPane((JFrame) Frame, load, color.MaterialColor.BLUEGREY_900, 0.8f);
        load = null;
        JMethods.updateInternalJTable(this, tabla);
        DATA = tabla.getDATA();
    }

    private void save() {
        for (int i = 0; i < tabla.getRowCount(); i++) {
            LISTA_DATA.add(new String[]{tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 2).toString()});
        }
        DETALLEENVIAR = JMethods.getDETALLE_Object_XML("fundousuario", "item", LISTA_DATA);

        if (!LISTA_DATA.isEmpty()) {
            ExecHTTP.ExecPostProcedure(Frame,
                    gettin_pages.api_set(),
                    new String[]{"iddatabase2", "query"},
                    new Object[]{
                        jkeys.IDDATABASE2,
                        ExecHTTP.parseQL("exec UpFundoUsuario", new Object[]{DETALLEENVIAR}
                        )
                    },
                    () -> {//ACTION DONE
                    },
                    () -> {//ACTION WARN
                    });

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        contenedor = new javax.swing.JPanel();
        panelGeneral = new views.JMPanel.JMPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new kevin.component.tabla.TablaSmart();
        toolbar1 = new kevin.component.toolbar.Toolbar();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Administracion de Fundos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        contenedor.setOpaque(false);
        contenedor.setLayout(new java.awt.BorderLayout());

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
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(panelGeneral, java.awt.BorderLayout.CENTER);

        jPanel1.add(contenedor, java.awt.BorderLayout.CENTER);

        toolbar1.setGUARDAR(true);
        toolbar1.setRECARGAR(true);
        toolbar1.setPreferredSize(new java.awt.Dimension(42, 240));
        jPanel1.add(toolbar1, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_F2) {
            JDialog.setDefaultLookAndFeelDecorated(false);
            JMethods.settingGlassPane((JFrame) Frame, buscarFundos, colorAccent, 0.6f);
            if (buscarFundos.DATA_SELECT != null) {
                int[] sel = tabla.getSelectedRows();
                for (int i = 0; i < sel.length; i++) {
                    tabla.setValueAt(buscarFundos.DATA_SELECT[0].toString(), sel[i], 2);
                    tabla.setValueAt(buscarFundos.DATA_SELECT[1].toString(), sel[i], 3);
                }
            }
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
    }//GEN-LAST:event_tablaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private views.JMPanel.JMPanel panelGeneral;
    private kevin.component.tabla.TablaSmart tabla;
    private kevin.component.toolbar.Toolbar toolbar1;
    // End of variables declaration//GEN-END:variables
}
