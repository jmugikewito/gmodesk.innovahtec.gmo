package gmo.panel;

import app.RunMain;
import gmo.classes.SincTareo;
import gmo.methods.jmethods;
import gmo.utils.jkeys;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import jmugi.voids.JCallback;
import kevin.component.frame.FrameDialog;

/**
 *
 * @author jmugi
 */
public class SincronizacionPanel extends javax.swing.JPanel {

    Window frame;

    JCallback callback;

    public SincronizacionPanel() {
        initComponents();

    }

    public SincronizacionPanel(Window frame) {
        initComponents();
        this.frame = frame;
        openInstanceSQLITE(frame);
        etDirBD.setText(RunMain.DIR_SQLITEDB);
        jmethods.cargarPlanillas(cboPlanilla, " 'u','9' ");
    }

    public SincronizacionPanel(Window frame, JCallback callbackDispose) {
        initComponents();
        this.frame = frame;
        this.callback = callbackDispose;
        openInstanceSQLITE(frame);
        etDirBD.setText(RunMain.DIR_SQLITEDB);
        jmethods.cargarPlanillas(cboPlanilla, " 'u','9' ");
    }

    public void openInstanceSQLITE(Window frame) {
        RunMain.SINC_SQLITE = new SincTareo(frame, RunMain.CONECT, jkeys.IDCLIENTE, jkeys.IDDATABASE, jkeys.IDUSUARIO, jkeys.IDEMPRESA);
    }

    public Window getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        openInstanceSQLITE(frame);
    }

    public static void viewSincronizacion(Window frameOrigen) {
        FrameDialog window = (frameOrigen == null ? new FrameDialog() : new FrameDialog((Frame) frameOrigen));
        SincronizacionPanel panelContenido = new SincronizacionPanel(frameOrigen, () -> {
            window.dispose();
        });
        window.setLayout(new BorderLayout());
        window.add(panelContenido);
        window.setSize(520, 660);
        window.setIconImage(new ImageIcon(window.getClass().getResource("/img/htec.png")).getImage());
        window.fadeIn();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAsistenciasExternas = new kevin.component.button.Button();
        jPanel1 = new javax.swing.JPanel();
        etDirBD = new kevin.component.label.Etiqueta();
        cboPlanilla = new kevin.component.combobox.ComboBox();
        border = new javax.swing.JPanel();
        btnContruirBD = new kevin.component.button.Button();
        btnParametros = new kevin.component.button.Button();
        button1 = new kevin.component.button.Button();
        button2 = new kevin.component.button.Button();
        button3 = new kevin.component.button.Button();
        button4 = new kevin.component.button.Button();
        button5 = new kevin.component.button.Button();
        button7 = new kevin.component.button.Button();
        button12 = new kevin.component.button.Button();
        button8 = new kevin.component.button.Button();
        button9 = new kevin.component.button.Button();
        button10 = new kevin.component.button.Button();
        button11 = new kevin.component.button.Button();
        button13 = new kevin.component.button.Button();
        btnEtapaCampoTurno = new kevin.component.button.Button();
        btnCultivosVariedades = new kevin.component.button.Button();
        btnResponsables = new kevin.component.button.Button();
        btnSemanasLocales = new kevin.component.button.Button();
        btnTransportistas = new kevin.component.button.Button();
        btnCosechaLabor = new kevin.component.button.Button();
        btnMotivos = new kevin.component.button.Button();
        btnUniNegocios = new kevin.component.button.Button();
        btnSubareas = new kevin.component.button.Button();
        btnUbigeo = new kevin.component.button.Button();
        btnGenero = new kevin.component.button.Button();
        btnNacionalidad = new kevin.component.button.Button();
        btnEstadoCivil = new kevin.component.button.Button();
        btnEstadoTrabajador = new kevin.component.button.Button();
        btnVia = new kevin.component.button.Button();
        btnZona = new kevin.component.button.Button();
        btnVinculoFamiliar = new kevin.component.button.Button();
        btnMoneda = new kevin.component.button.Button();
        btnBanco = new kevin.component.button.Button();
        btnCargo = new kevin.component.button.Button();
        btnTipoDocumentos = new kevin.component.button.Button();
        buttonMaterialIcon1 = new kevin.component.button.ButtonMaterialIcon();

        btnAsistenciasExternas.setBackground(new java.awt.Color(0, 182, 134));
        btnAsistenciasExternas.setText("Asistencias Externas");
        btnAsistenciasExternas.setPreferredSize(new java.awt.Dimension(160, 36));
        btnAsistenciasExternas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsistenciasExternasActionPerformed(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(400, 400));
        setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(371, 82));

        etDirBD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etDirBD.setText("");
        etDirBD.setMaximumSize(new java.awt.Dimension(240, 18));
        etDirBD.setMinimumSize(new java.awt.Dimension(240, 18));
        etDirBD.setPreferredSize(new java.awt.Dimension(240, 18));

        cboPlanilla.setPreferredSize(new java.awt.Dimension(23, 42));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPlanilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(etDirBD, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(etDirBD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(cboPlanilla, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        border.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        border.setOpaque(false);
        border.setLayout(new java.awt.GridLayout(10, 4));

        btnContruirBD.setBackground(new java.awt.Color(236, 64, 122));
        btnContruirBD.setText("Constuir BD");
        btnContruirBD.setPreferredSize(new java.awt.Dimension(120, 36));
        btnContruirBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContruirBDActionPerformed(evt);
            }
        });
        border.add(btnContruirBD);

        btnParametros.setBackground(new java.awt.Color(255, 204, 0));
        btnParametros.setText("Parametros");
        btnParametros.setPreferredSize(new java.awt.Dimension(120, 36));
        btnParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametrosActionPerformed(evt);
            }
        });
        border.add(btnParametros);

        button1.setText("Servidores");
        button1.setPreferredSize(new java.awt.Dimension(120, 36));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        border.add(button1);

        button2.setText("Base de Datos");
        button2.setPreferredSize(new java.awt.Dimension(120, 36));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        border.add(button2);

        button3.setText("Empresas");
        button3.setPreferredSize(new java.awt.Dimension(120, 36));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        border.add(button3);

        button4.setText("Tipos de Usuario");
        button4.setPreferredSize(new java.awt.Dimension(120, 36));
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        border.add(button4);

        button5.setText("Usuarios");
        button5.setPreferredSize(new java.awt.Dimension(120, 36));
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        border.add(button5);

        button7.setBackground(new java.awt.Color(0, 182, 134));
        button7.setText("Planillas");
        button7.setPreferredSize(new java.awt.Dimension(130, 36));
        button7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button7ActionPerformed(evt);
            }
        });
        border.add(button7);

        button12.setBackground(new java.awt.Color(0, 182, 134));
        button12.setText("Trabajadores");
        button12.setPreferredSize(new java.awt.Dimension(130, 36));
        button12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button12ActionPerformed(evt);
            }
        });
        border.add(button12);

        button8.setBackground(new java.awt.Color(0, 182, 134));
        button8.setText("Actividades");
        button8.setPreferredSize(new java.awt.Dimension(130, 36));
        button8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button8ActionPerformed(evt);
            }
        });
        border.add(button8);

        button9.setBackground(new java.awt.Color(0, 182, 134));
        button9.setText("Labores");
        button9.setPreferredSize(new java.awt.Dimension(130, 36));
        button9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button9ActionPerformed(evt);
            }
        });
        border.add(button9);

        button10.setBackground(new java.awt.Color(0, 182, 134));
        button10.setText("Consumidores");
        button10.setPreferredSize(new java.awt.Dimension(130, 36));
        button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button10ActionPerformed(evt);
            }
        });
        border.add(button10);

        button11.setBackground(new java.awt.Color(0, 182, 134));
        button11.setText("Turnos");
        button11.setPreferredSize(new java.awt.Dimension(100, 36));
        button11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button11ActionPerformed(evt);
            }
        });
        border.add(button11);

        button13.setBackground(new java.awt.Color(0, 182, 134));
        button13.setText("Conceptos");
        button13.setEnabled(false);
        button13.setPreferredSize(new java.awt.Dimension(120, 36));
        button13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button13ActionPerformed(evt);
            }
        });
        border.add(button13);

        btnEtapaCampoTurno.setBackground(new java.awt.Color(63, 81, 181));
        btnEtapaCampoTurno.setText("Etapas/Campo/Turno");
        btnEtapaCampoTurno.setPreferredSize(new java.awt.Dimension(160, 36));
        btnEtapaCampoTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEtapaCampoTurnoActionPerformed(evt);
            }
        });
        border.add(btnEtapaCampoTurno);

        btnCultivosVariedades.setBackground(new java.awt.Color(63, 81, 181));
        btnCultivosVariedades.setText("Cultivos/Variedades");
        btnCultivosVariedades.setPreferredSize(new java.awt.Dimension(160, 36));
        btnCultivosVariedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCultivosVariedadesActionPerformed(evt);
            }
        });
        border.add(btnCultivosVariedades);

        btnResponsables.setBackground(new java.awt.Color(63, 81, 181));
        btnResponsables.setText("Responsables");
        btnResponsables.setPreferredSize(new java.awt.Dimension(120, 36));
        btnResponsables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResponsablesActionPerformed(evt);
            }
        });
        border.add(btnResponsables);

        btnSemanasLocales.setBackground(new java.awt.Color(63, 81, 181));
        btnSemanasLocales.setText("Semanas");
        btnSemanasLocales.setPreferredSize(new java.awt.Dimension(100, 36));
        btnSemanasLocales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemanasLocalesActionPerformed(evt);
            }
        });
        border.add(btnSemanasLocales);

        btnTransportistas.setBackground(new java.awt.Color(63, 81, 181));
        btnTransportistas.setText("Transportistas");
        btnTransportistas.setPreferredSize(new java.awt.Dimension(120, 36));
        btnTransportistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransportistasActionPerformed(evt);
            }
        });
        border.add(btnTransportistas);

        btnCosechaLabor.setBackground(new java.awt.Color(63, 81, 181));
        btnCosechaLabor.setText("Cosecha-Labor");
        btnCosechaLabor.setPreferredSize(new java.awt.Dimension(120, 36));
        btnCosechaLabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCosechaLaborActionPerformed(evt);
            }
        });
        border.add(btnCosechaLabor);

        btnMotivos.setBackground(new java.awt.Color(63, 81, 181));
        btnMotivos.setText("Motivos");
        btnMotivos.setPreferredSize(new java.awt.Dimension(100, 36));
        btnMotivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMotivosActionPerformed(evt);
            }
        });
        border.add(btnMotivos);

        btnUniNegocios.setBackground(new java.awt.Color(63, 81, 181));
        btnUniNegocios.setText("Unidades-Negocio");
        btnUniNegocios.setEnabled(false);
        btnUniNegocios.setPreferredSize(new java.awt.Dimension(160, 36));
        btnUniNegocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUniNegociosActionPerformed(evt);
            }
        });
        border.add(btnUniNegocios);

        btnSubareas.setBackground(new java.awt.Color(63, 81, 181));
        btnSubareas.setText("SubAreas");
        btnSubareas.setPreferredSize(new java.awt.Dimension(100, 36));
        btnSubareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubareasActionPerformed(evt);
            }
        });
        border.add(btnSubareas);

        btnUbigeo.setBackground(new java.awt.Color(63, 81, 181));
        btnUbigeo.setText("Ubigeo");
        btnUbigeo.setPreferredSize(new java.awt.Dimension(100, 36));
        btnUbigeo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUbigeoMouseClicked(evt);
            }
        });
        btnUbigeo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbigeoActionPerformed(evt);
            }
        });
        border.add(btnUbigeo);

        btnGenero.setBackground(new java.awt.Color(63, 81, 181));
        btnGenero.setText("Genero");
        btnGenero.setPreferredSize(new java.awt.Dimension(100, 36));
        btnGenero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGeneroMouseClicked(evt);
            }
        });
        btnGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneroActionPerformed(evt);
            }
        });
        border.add(btnGenero);

        btnNacionalidad.setBackground(new java.awt.Color(63, 81, 181));
        btnNacionalidad.setText("Nacionalidad");
        btnNacionalidad.setPreferredSize(new java.awt.Dimension(100, 36));
        btnNacionalidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNacionalidadMouseClicked(evt);
            }
        });
        btnNacionalidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNacionalidadActionPerformed(evt);
            }
        });
        border.add(btnNacionalidad);

        btnEstadoCivil.setBackground(new java.awt.Color(63, 81, 181));
        btnEstadoCivil.setText("Estado civil");
        btnEstadoCivil.setPreferredSize(new java.awt.Dimension(100, 36));
        btnEstadoCivil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstadoCivilMouseClicked(evt);
            }
        });
        btnEstadoCivil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoCivilActionPerformed(evt);
            }
        });
        border.add(btnEstadoCivil);

        btnEstadoTrabajador.setBackground(new java.awt.Color(63, 81, 181));
        btnEstadoTrabajador.setText("Estado trabajador");
        btnEstadoTrabajador.setPreferredSize(new java.awt.Dimension(100, 36));
        btnEstadoTrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEstadoTrabajadorMouseClicked(evt);
            }
        });
        btnEstadoTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadoTrabajadorActionPerformed(evt);
            }
        });
        border.add(btnEstadoTrabajador);

        btnVia.setBackground(new java.awt.Color(63, 81, 181));
        btnVia.setText("Via");
        btnVia.setPreferredSize(new java.awt.Dimension(100, 36));
        btnVia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViaMouseClicked(evt);
            }
        });
        btnVia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViaActionPerformed(evt);
            }
        });
        border.add(btnVia);

        btnZona.setBackground(new java.awt.Color(63, 81, 181));
        btnZona.setText("Zona");
        btnZona.setPreferredSize(new java.awt.Dimension(100, 36));
        btnZona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnZonaMouseClicked(evt);
            }
        });
        btnZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZonaActionPerformed(evt);
            }
        });
        border.add(btnZona);

        btnVinculoFamiliar.setBackground(new java.awt.Color(63, 81, 181));
        btnVinculoFamiliar.setText("Vinculo familiar");
        btnVinculoFamiliar.setPreferredSize(new java.awt.Dimension(100, 36));
        btnVinculoFamiliar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVinculoFamiliarMouseClicked(evt);
            }
        });
        btnVinculoFamiliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVinculoFamiliarActionPerformed(evt);
            }
        });
        border.add(btnVinculoFamiliar);

        btnMoneda.setBackground(new java.awt.Color(63, 81, 181));
        btnMoneda.setText("Moneda");
        btnMoneda.setPreferredSize(new java.awt.Dimension(100, 36));
        btnMoneda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMonedaMouseClicked(evt);
            }
        });
        btnMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonedaActionPerformed(evt);
            }
        });
        border.add(btnMoneda);

        btnBanco.setBackground(new java.awt.Color(63, 81, 181));
        btnBanco.setText("Banco");
        btnBanco.setPreferredSize(new java.awt.Dimension(100, 36));
        btnBanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBancoMouseClicked(evt);
            }
        });
        btnBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBancoActionPerformed(evt);
            }
        });
        border.add(btnBanco);

        btnCargo.setBackground(new java.awt.Color(63, 81, 181));
        btnCargo.setText("Cargo");
        btnCargo.setPreferredSize(new java.awt.Dimension(100, 36));
        btnCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargoMouseClicked(evt);
            }
        });
        btnCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargoActionPerformed(evt);
            }
        });
        border.add(btnCargo);

        btnTipoDocumentos.setBackground(new java.awt.Color(63, 81, 181));
        btnTipoDocumentos.setText("Tipos Documentos");
        btnTipoDocumentos.setPreferredSize(new java.awt.Dimension(100, 36));
        btnTipoDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTipoDocumentosMouseClicked(evt);
            }
        });
        btnTipoDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoDocumentosActionPerformed(evt);
            }
        });
        border.add(btnTipoDocumentos);

        buttonMaterialIcon1.setText("buttonMaterialIcon1");
        buttonMaterialIcon1.setICO(iconfont.MATERIALICON.MATERIALICONIC.CLOSE);
        buttonMaterialIcon1.setPreferredSize(new java.awt.Dimension(220, 36));
        buttonMaterialIcon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaterialIcon1ActionPerformed(evt);
            }
        });
        border.add(buttonMaterialIcon1);

        add(border, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.SERVIDORES);
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.BASES_DE_DATOS);
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.EMPRESAS);
    }//GEN-LAST:event_button3ActionPerformed

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.TIPOS_DE_USUARIO);
    }//GEN-LAST:event_button4ActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.USUARIOS);
    }//GEN-LAST:event_button5ActionPerformed

    private void button7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button7ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.PLANILLAS);
    }//GEN-LAST:event_button7ActionPerformed

    private void button8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button8ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ACTIVIDADES);
    }//GEN-LAST:event_button8ActionPerformed

    private void button9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button9ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.LABORES);
    }//GEN-LAST:event_button9ActionPerformed

    private void button12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button12ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.TRABAJADORES);
    }//GEN-LAST:event_button12ActionPerformed

    private void button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button10ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.CONSUMIDORES);
    }//GEN-LAST:event_button10ActionPerformed

    private void button11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button11ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.TURNOS);
    }//GEN-LAST:event_button11ActionPerformed

    private void button13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button13ActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.CONCEPTOS);
    }//GEN-LAST:event_button13ActionPerformed

    private void buttonMaterialIcon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaterialIcon1ActionPerformed
        if (callback != null) {
            callback.action();
        }
    }//GEN-LAST:event_buttonMaterialIcon1ActionPerformed

    private void btnContruirBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContruirBDActionPerformed
        RunMain.createDatabase(frame);
    }//GEN-LAST:event_btnContruirBDActionPerformed

    private void btnEtapaCampoTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEtapaCampoTurnoActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ETAPACAMPOTURNO);
    }//GEN-LAST:event_btnEtapaCampoTurnoActionPerformed

    private void btnCultivosVariedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCultivosVariedadesActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.CULTIVOSVARIEDADES);
    }//GEN-LAST:event_btnCultivosVariedadesActionPerformed

    private void btnResponsablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResponsablesActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.REPONSABLES);
    }//GEN-LAST:event_btnResponsablesActionPerformed

    private void btnParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnParametrosActionPerformed

    private void btnSemanasLocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemanasLocalesActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.SEMANASLOCALES);
    }//GEN-LAST:event_btnSemanasLocalesActionPerformed

    private void btnTransportistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransportistasActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.TRANSPORTISTAS);
    }//GEN-LAST:event_btnTransportistasActionPerformed

    private void btnCosechaLaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCosechaLaborActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.COSECHALABOR);
    }//GEN-LAST:event_btnCosechaLaborActionPerformed

    private void btnMotivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMotivosActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.MOTIVOS);
    }//GEN-LAST:event_btnMotivosActionPerformed

    private void btnUniNegociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUniNegociosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUniNegociosActionPerformed

    private void btnSubareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubareasActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.SUBAREAS);
    }//GEN-LAST:event_btnSubareasActionPerformed

    private void btnAsistenciasExternasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsistenciasExternasActionPerformed
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ASISEXTERNAS);
    }//GEN-LAST:event_btnAsistenciasExternasActionPerformed

    private void btnUbigeoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbigeoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbigeoActionPerformed

    private void btnUbigeoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUbigeoMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.UBIGEO);
    }//GEN-LAST:event_btnUbigeoMouseClicked

    private void btnGeneroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGeneroMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.GENERO);
    }//GEN-LAST:event_btnGeneroMouseClicked

    private void btnGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGeneroActionPerformed

    private void btnNacionalidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNacionalidadMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.NACIONALIDAD);
    }//GEN-LAST:event_btnNacionalidadMouseClicked

    private void btnNacionalidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNacionalidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNacionalidadActionPerformed

    private void btnEstadoCivilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadoCivilMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ESTADOCIVIL);
    }//GEN-LAST:event_btnEstadoCivilMouseClicked

    private void btnEstadoCivilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoCivilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEstadoCivilActionPerformed

    private void btnEstadoTrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEstadoTrabajadorMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ESTADOTRABAJADOR);
    }//GEN-LAST:event_btnEstadoTrabajadorMouseClicked

    private void btnEstadoTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadoTrabajadorActionPerformed

    }//GEN-LAST:event_btnEstadoTrabajadorActionPerformed

    private void btnViaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViaMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.VIA);
    }//GEN-LAST:event_btnViaMouseClicked

    private void btnViaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnViaActionPerformed

    private void btnZonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnZonaMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.ZONA);
    }//GEN-LAST:event_btnZonaMouseClicked

    private void btnZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnZonaActionPerformed

    private void btnVinculoFamiliarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVinculoFamiliarMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.VINCULOFAMILIAR);
    }//GEN-LAST:event_btnVinculoFamiliarMouseClicked

    private void btnVinculoFamiliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVinculoFamiliarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVinculoFamiliarActionPerformed

    private void btnMonedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMonedaMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.MONEDA);
    }//GEN-LAST:event_btnMonedaMouseClicked

    private void btnMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonedaActionPerformed

    }//GEN-LAST:event_btnMonedaActionPerformed

    private void btnBancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBancoMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.BANCO);
    }//GEN-LAST:event_btnBancoMouseClicked

    private void btnBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBancoActionPerformed

    private void btnCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargoMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.CARGO);
    }//GEN-LAST:event_btnCargoMouseClicked

    private void btnCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCargoActionPerformed

    private void btnTipoDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTipoDocumentosMouseClicked
        RunMain.SINC_SQLITE.download(SincTareo.TYPEDOWN.TIPODOCUMENTOS);
    }//GEN-LAST:event_btnTipoDocumentosMouseClicked

    private void btnTipoDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoDocumentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTipoDocumentosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel border;
    private kevin.component.button.Button btnAsistenciasExternas;
    private kevin.component.button.Button btnBanco;
    private kevin.component.button.Button btnCargo;
    private kevin.component.button.Button btnContruirBD;
    private kevin.component.button.Button btnCosechaLabor;
    private kevin.component.button.Button btnCultivosVariedades;
    private kevin.component.button.Button btnEstadoCivil;
    private kevin.component.button.Button btnEstadoTrabajador;
    private kevin.component.button.Button btnEtapaCampoTurno;
    private kevin.component.button.Button btnGenero;
    private kevin.component.button.Button btnMoneda;
    private kevin.component.button.Button btnMotivos;
    private kevin.component.button.Button btnNacionalidad;
    private kevin.component.button.Button btnParametros;
    private kevin.component.button.Button btnResponsables;
    private kevin.component.button.Button btnSemanasLocales;
    private kevin.component.button.Button btnSubareas;
    private kevin.component.button.Button btnTipoDocumentos;
    private kevin.component.button.Button btnTransportistas;
    private kevin.component.button.Button btnUbigeo;
    private kevin.component.button.Button btnUniNegocios;
    private kevin.component.button.Button btnVia;
    private kevin.component.button.Button btnVinculoFamiliar;
    private kevin.component.button.Button btnZona;
    private kevin.component.button.Button button1;
    private kevin.component.button.Button button10;
    private kevin.component.button.Button button11;
    private kevin.component.button.Button button12;
    private kevin.component.button.Button button13;
    private kevin.component.button.Button button2;
    private kevin.component.button.Button button3;
    private kevin.component.button.Button button4;
    private kevin.component.button.Button button5;
    private kevin.component.button.Button button7;
    private kevin.component.button.Button button8;
    private kevin.component.button.Button button9;
    private kevin.component.button.ButtonMaterialIcon buttonMaterialIcon1;
    private kevin.component.combobox.ComboBox cboPlanilla;
    private kevin.component.label.Etiqueta etDirBD;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
