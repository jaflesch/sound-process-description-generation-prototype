/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * T2Pgui.java
 *
 * Created on 16.07.2010, 16:18:44
 */

package com.inubit.research.textToProcess.gui;

import com.inubit.research.gui.Workbench;
import com.inubit.research.textToProcess.Constants;
import com.inubit.research.textToProcess.TextStatistics;
import com.inubit.research.textToProcess.TextToProcess;
import com.inubit.research.textToProcess.TextToProcessListener;
import com.inubit.research.textToProcess.processing.FrameNetWrapper;
import com.inubit.research.textToProcess.processing.ITextParsingStatusListener;
import com.inubit.research.textToProcess.processing.WordNetWrapper;
import com.inubit.research.textToProcess.textModel.TextModel;
import com.inubit.research.textToProcess.textModel.TextModelControler;
import com.inubit.research.textToProcess.worldModel.Actor;
import com.inubit.research.textToProcess.worldModel.Resource;
import com.inubit.research.textToProcess.worldModel.SpecifiedElement;
import com.inubit.research.util.SwingUtils;
import com.inubit.research.util.WaitDialog;
import edu.stanford.nlp.io.ExtensionFileFilter;
import edu.stanford.nlp.trees.Tree;
import net.frapu.code.visualization.*;
import net.frapu.code.visualization.bpmn.BPMNModel;
import net.frapu.code.visualization.bpmn.FlowObject;
import net.frapu.code.visualization.bpmn.Gateway;
import net.frapu.code.visualization.helper.EmptyNodeOnSelectMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

//import com.inubit.research.gui.plugins.variants.HeuristicModelDiff;
//import com.inubit.research.gui.plugins.variants.MatchedPair;

/**
 * @author ff
 */
public class T2Pgui extends javax.swing.JFrame implements ProcessEditorListener, TextToProcessListener {
    /**
     *
     */
    private static final long serialVersionUID = 9094424384839847148L;
    private TextModelControler f_textModelControler;
    private File f_lastDirectoryText = null;
    private File f_lastDirectoryCompare = null;
    private File f_lastFile;
    private ProcessEditor f_processEditor;
    private TextToProcess f_processor;

    private BPMNModel f_generatedModel;
    private DecimalFormat f_compareFormat = new DecimalFormat("00.00%");

    private Workbench f_mainWorkbench;
    private FlowObject f_highlightNode;
    private LaneSplitOffControler f_lsoControler;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton f_bAnalyse;
    private javax.swing.JButton f_bParse;
    private net.frapu.code.visualization.ProcessEditor f_editor;
    private javax.swing.JMenu f_mAdd;
    private javax.swing.JMenuItem f_miAddPCL;
    private javax.swing.JMenuItem f_miOpen;
    private javax.swing.JMenuItem f_miPDF;
    private javax.swing.JMenuItem f_miPreferences;
    private javax.swing.JMenuItem f_miTextStatistics;
    private javax.swing.JPanel f_panelLeft;
    private javax.swing.JToggleButton f_showLinks;
    private javax.swing.JToggleButton f_showRefs;
    private javax.swing.JSplitPane f_splitPane;
    private javax.swing.JTextPane f_textPaneDependencies;
    private javax.swing.JToolBar f_toolbar;
    private javax.swing.JScrollPane f_tpDScrollPane;
    private edu.stanford.nlp.parser.ui.TreeJPanel f_treePanel;
    private javax.swing.JPopupMenu f_treePopup;


    //	private HeuristicModelDiff compare(BPMNModel model1,BPMNModel gen) {
//		HeuristicModelDiff _differ = new HeuristicModelDiff(model1,gen);
//		System.out.println("Mapping:");
//		System.out.println(_differ.toString());
//		System.out.println("---------------------");
//		System.out.println("Unmatched Nodes: ");
//		ArrayList<ProcessNode> _nodes = new ArrayList<ProcessNode>(model1.getNodes());
//		for(MatchedPair mp:_differ) {
//			_nodes.remove(mp.getObject1());
//		}
//		for(ProcessNode pn:_nodes) {
//			System.out.println(pn);
//		}
//		System.out.println("Graph-Edit-Distance: "+_differ.getGraphEditDistance());
//		System.out.println("Nodes(gen, hum): "+gen.getNodes().size()+" - "+model1.getNodes().size());
//		System.out.println("\tGateways (gen, hum): "+countGateways(gen.getNodes())+" - "+countGateways(model1.getNodes()));
//		System.out.println("Edges (gen, hum): "+gen.getEdges().size()+" - "+model1.getEdges().size());
//		return _differ;
//	}
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;

    /**
     * Creates new form T2Pgui
     */
    public T2Pgui() {
        createDefaultSettings();
        WordNetWrapper.init();
        FrameNetWrapper.init();
        f_textModelControler = new TextModelControler();
        f_processor = new TextToProcess(this, f_textModelControler, f_lsoControler);
        f_textModelControler.setTextToprocess(f_processor);
        initComponents();
        f_editor.setLayoutEdges(false);
        f_editor.addListener(this);
        f_editor.addListener(f_textModelControler);
        f_editor.setNodeOnSelectMenu(new EmptyNodeOnSelectMenu(f_editor));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new T2Pgui().setVisible(true);
            }
        });
    }

    /**
     *
     */
    private void createDefaultSettings() {
        Configuration _conf = Configuration.getInstance();
        if (_conf.getProperty(Constants.CONF_GENERATE_ADD_UNKNOWN_PT).length() == 0) {
            _conf.setProperty(Constants.CONF_GENERATE_ADD_UNKNOWN_PT, "1");
        }
        if (_conf.getProperty(Constants.CONF_GENERATE_BB_POOLS).length() == 0) {
            _conf.setProperty(Constants.CONF_GENERATE_BB_POOLS, "1");
        }
        if (_conf.getProperty(Constants.CONF_GENERATE_DATA_OBJECTS).length() == 0) {
            _conf.setProperty(Constants.CONF_GENERATE_DATA_OBJECTS, "0");
        }
        if (_conf.getProperty(Constants.CONF_GENERATE_HIGHLIGHT_META_ACTIONS).length() == 0) {
            _conf.setProperty(Constants.CONF_GENERATE_HIGHLIGHT_META_ACTIONS, "0");
        }
        if (_conf.getProperty(Constants.CONF_GENERATE_REMOVE_LOW_ENT_NODES).length() == 0) {
            _conf.setProperty(Constants.CONF_GENERATE_REMOVE_LOW_ENT_NODES, "1");
        }
        if (_conf.getProperty(Constants.CONF_LANESO_ALWAYS_CREATE_MSG_EVT).length() == 0) {
            _conf.setProperty(Constants.CONF_LANESO_ALWAYS_CREATE_MSG_EVT, "0");
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        f_treePopup = new javax.swing.JPopupMenu();
        f_miPDF = new javax.swing.JMenuItem();
        f_splitPane = new javax.swing.JSplitPane();
        f_panelLeft = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        f_editor = new net.frapu.code.visualization.ProcessEditor(new TextModel());
        jLayeredPane1 = new javax.swing.JLayeredPane();
        f_toolbar = new javax.swing.JToolBar();
        f_bParse = new javax.swing.JButton();
        f_bAnalyse = new javax.swing.JButton();
        f_showRefs = new javax.swing.JToggleButton();
        f_showLinks = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        f_tpDScrollPane = new javax.swing.JScrollPane();
        f_textPaneDependencies = new javax.swing.JTextPane();
        f_treePanel = new edu.stanford.nlp.parser.ui.TreeJPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        f_miOpen = new javax.swing.JMenuItem();
        f_miPreferences = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        f_miTextStatistics = new javax.swing.JMenuItem();
        f_mAdd = new javax.swing.JMenu();
        f_miAddPCL = new javax.swing.JMenuItem();

        f_miPDF.setText("Save as pdf...");
        f_miPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_miPDFActionPerformed(evt);
            }
        });
        f_treePopup.add(f_miPDF);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text to Process Conversion Plugin");

        f_splitPane.setDividerLocation(400);
        f_splitPane.setDividerSize(3);

        f_editor.setAutoscrolls(true);

        javax.swing.GroupLayout f_editorLayout = new javax.swing.GroupLayout(f_editor);
        f_editor.setLayout(f_editorLayout);
        f_editorLayout.setHorizontalGroup(
                f_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 395, Short.MAX_VALUE)
        );
        f_editorLayout.setVerticalGroup(
                f_editorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 479, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(f_editor);

        f_toolbar.setFloatable(false);
        f_toolbar.setRollover(true);
        f_toolbar.setMaximumSize(new java.awt.Dimension(369, 29));
        f_toolbar.setMinimumSize(new java.awt.Dimension(348, 29));
        f_toolbar.setPreferredSize(new java.awt.Dimension(369, 29));

        f_bParse.setText("Parse");
        f_bParse.setMaximumSize(new java.awt.Dimension(45, 25));
        f_bParse.setMinimumSize(new java.awt.Dimension(39, 20));
        f_bParse.setPreferredSize(new java.awt.Dimension(45, 25));
        f_bParse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_bParseActionPerformed(evt);
            }
        });
        f_toolbar.add(f_bParse);

        f_bAnalyse.setText("Analyse Text");
        f_bAnalyse.setFocusable(false);
        f_bAnalyse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        f_bAnalyse.setMaximumSize(new java.awt.Dimension(80, 25));
        f_bAnalyse.setPreferredSize(new java.awt.Dimension(80, 25));
        f_bAnalyse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        f_bAnalyse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_bAnalyseActionPerformed(evt);
            }
        });
        f_toolbar.add(f_bAnalyse);

        f_showRefs.setSelected(true);
        f_showRefs.setText("show References");
        f_showRefs.setFocusable(false);
        f_showRefs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        f_showRefs.setMaximumSize(new java.awt.Dimension(110, 25));
        f_showRefs.setPreferredSize(new java.awt.Dimension(110, 25));
        f_showRefs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        f_showRefs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_showRefsActionPerformed(evt);
            }
        });
        f_toolbar.add(f_showRefs);

        f_showLinks.setSelected(true);
        f_showLinks.setText("show Links");
        f_showLinks.setFocusable(false);
        f_showLinks.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        f_showLinks.setMaximumSize(new java.awt.Dimension(75, 25));
        f_showLinks.setPreferredSize(new java.awt.Dimension(77, 25));
        f_showLinks.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        f_showLinks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_showLinksActionPerformed(evt);
            }
        });
        f_toolbar.add(f_showLinks);

        jButton1.setText("compare...");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(75, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(65, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        f_toolbar.add(jButton1);

        f_toolbar.setBounds(0, 0, 400, 30);
        jLayeredPane1.add(f_toolbar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout f_panelLeftLayout = new javax.swing.GroupLayout(f_panelLeft);
        f_panelLeft.setLayout(f_panelLeftLayout);
        f_panelLeftLayout.setHorizontalGroup(
                f_panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                        .addGroup(f_panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );
        f_panelLeftLayout.setVerticalGroup(
                f_panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(f_panelLeftLayout.createSequentialGroup()
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(482, Short.MAX_VALUE))
                        .addGroup(f_panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, f_panelLeftLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                                        .addGap(0, 0, 0)))
        );

        f_splitPane.setLeftComponent(f_panelLeft);

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(3);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        f_tpDScrollPane.setPreferredSize(new java.awt.Dimension(150, 200));
        f_tpDScrollPane.setViewportView(f_textPaneDependencies);

        jSplitPane1.setBottomComponent(f_tpDScrollPane);

        f_treePanel.setBackground(new java.awt.Color(255, 255, 255));
        f_treePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        f_treePanel.setMinimumSize(new java.awt.Dimension(100, 100));
        f_treePanel.setPreferredSize(new java.awt.Dimension(100, 100));
        f_treePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                f_treePanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout f_treePanelLayout = new javax.swing.GroupLayout(f_treePanel);
        f_treePanel.setLayout(f_treePanelLayout);
        f_treePanelLayout.setHorizontalGroup(
                f_treePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 126, Short.MAX_VALUE)
        );
        f_treePanelLayout.setVerticalGroup(
                f_treePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 195, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(f_treePanel);

        f_splitPane.setRightComponent(jSplitPane1);

        jMenu1.setText("File");

        f_miOpen.setText("Open Text...");
        f_miOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_miOpenActionPerformed(evt);
            }
        });
        jMenu1.add(f_miOpen);

        f_miPreferences.setText("Preferences...");
        f_miPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_miPreferencesActionPerformed(evt);
            }
        });
        jMenu1.add(f_miPreferences);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Information");

        jMenuItem1.setText("Show Actors...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Show Resources..");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        f_miTextStatistics.setText("Text Statistics");
        f_miTextStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_miTextStatisticsActionPerformed(evt);
            }
        });
        jMenu2.add(f_miTextStatistics);

        jMenuBar1.add(jMenu2);

        f_mAdd.setText("Add");

        f_miAddPCL.setText("Add To Person Corrector List");
        f_miAddPCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                f_miAddPCLActionPerformed(evt);
            }
        });
        f_mAdd.add(f_miAddPCL);

        jMenuBar1.add(f_mAdd);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(f_splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(f_splitPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void f_miOpenActionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_miOpenActionPerformed
        JFileChooser _chooseFile = new JFileChooser(f_lastDirectoryText);
        _chooseFile.setFileFilter(new ExtensionFileFilter("txt", true));
        if (!(_chooseFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)) {
            return;
        }
        File _f = _chooseFile.getSelectedFile();
        System.out.println(_f.getAbsolutePath());
        f_lastDirectoryText = _f.getParentFile();
        loadText(_f);

    }//GEN-LAST:event_f_miOpenActionPerformed

    private void f_treePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_f_treePanelMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            f_treePopup.show(f_treePanel, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_f_treePanelMouseClicked

    private void f_miPDFActionPerformed(@SuppressWarnings("unused") java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_miPDFActionPerformed
        JFileChooser _choose = new JFileChooser();
        if (_choose.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                PDFExporter.writeToFile(_choose.getSelectedFile(), f_treePanel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_f_miPDFActionPerformed

    /**
     * @param evt
     */
    private void f_bParseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_bParseActionPerformed
        loadText(f_lastFile);
    }//GEN-LAST:event_f_bParseActionPerformed

    /**
     * @param evt
     */
    private void f_bAnalyseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_bAnalyseActionPerformed
        long _start = System.currentTimeMillis();
        f_processor.analyzeText(true);
        System.out.println("Text analyzed in: " + (System.currentTimeMillis() - _start) + " ms");
    }//GEN-LAST:event_f_bAnalyseActionPerformed

    /**
     * @param evt
     */
    private void f_showRefsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_showRefsActionPerformed
        f_textModelControler.setShowReferences(f_editor.getModel(), f_showRefs.isSelected());
        f_editor.repaint();
    }//GEN-LAST:event_f_showRefsActionPerformed

    /**
     * @param evt
     */
    private void f_showLinksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_showLinksActionPerformed
        f_textModelControler.setShowLinks(f_editor.getModel(), f_showLinks.isSelected());
        f_editor.repaint();
    }//GEN-LAST:event_f_showLinksActionPerformed

    public boolean getShowLinks() {
        return f_showLinks.isSelected();
    }

    public boolean getShowRefs() {
        return f_showRefs.isSelected();
    }

    /**
     * compare button was clicked
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JOptionPane.showMessageDialog(this, "Sorry we code to compare model similarity is not provided as part of the project");
//    	File _f = ConverterHelper.pickFileForImport(f_lastDirectoryCompare);
//    	if(_f != null) {
//    		f_lastDirectoryCompare = _f.getParentFile();
//	    	List<ProcessModel> _models;
//			try {
//				_models = ConverterHelper.importModels(_f);
//				if(_models.get(0) instanceof BPMNModel) {
//					HeuristicModelDiff.setUseIDAnalysis(false);
//					HeuristicModelDiff.setUseContextAnalysis(true);
//					HeuristicModelDiff.setUseAttributeAnalysis(true);
//					HeuristicModelDiff.setUseSemanticLabelAnalysis(false);
//					HeuristicModelDiff.setUseSyntacticLabelAnalysis(true);
//
//					HeuristicModelDiff.setWeightskippedVertices(0.3);
//					HeuristicModelDiff.setWeightskippedEdges(0.3);
//					HeuristicModelDiff.setWeightsubstitutedVertices(0.4);
//					HeuristicModelDiff.setNotConsideredCutOffSimilarity(0.001);
//
//		    		HeuristicModelDiff _result = compare(f_generatedModel,(BPMNModel) _models.get(0));
//		    		_result = compare((BPMNModel) _models.get(0),f_generatedModel);
//		    		JOptionPane.showMessageDialog(this,
//		    				"Similarity: "+f_compareFormat.format(_result.getGraphEditDistance()), "Compare Result", JOptionPane.INFORMATION_MESSAGE);
//		    	}else {
//		    		JOptionPane.showMessageDialog(this, "This is not a BPMN-Model file");
//		    	}
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(this, "The file could not be imported!");
//				e.printStackTrace();
//			}
//
//    	}
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param evt
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //show actors
        SimpleListView _view = new SimpleListView(this, false);
        for (Actor a : f_processor.getAnalyzer().getWorld().getActors()) {
            _view.addLine(a);
        }
        _view.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param evt
     */
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //show resources
        SimpleListView _view = new SimpleListView(this, false);
        for (Resource a : f_processor.getAnalyzer().getWorld().getResources()) {
            _view.addLine(a);
        }
        _view.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param evt
     */
    private void f_miPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_miPreferencesActionPerformed
        PreferencesGUI _pgui = new PreferencesGUI(this, true);
        SwingUtils.center(_pgui);
        _pgui.setVisible(true);
    }//GEN-LAST:event_f_miPreferencesActionPerformed

    /**
     * @param evt
     */
    private void f_miTextStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_miTextStatisticsActionPerformed
        SimpleListView _slv = new SimpleListView(this, false);
        TextStatistics _stats = f_processor.getTextStatistics();
        _slv.addLine("Number of Sentences: " + _stats.getNumberOfSentences());
        _slv.addLine("Average Sentence Length: " + _stats.getAvgSentenceLength());
        _slv.addLine("Number of Relative References: " + _stats.getNumOfReferences());
        _slv.addLine("Number of Links: " + _stats.getNumOfLinks());
        _slv.setVisible(true);

    }//GEN-LAST:event_f_miTextStatisticsActionPerformed

    /**
     * @param evt
     */
    private void f_miAddPCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_f_miAddPCLActionPerformed
        String _entry = JOptionPane.showInputDialog(this, "Please enter a new entry for the Person Corrector List:");
        if (_entry != null && !_entry.isEmpty()) {
            Constants.f_personCorrectorList.add(_entry.toLowerCase());
        }
    }//GEN-LAST:event_f_miAddPCLActionPerformed

    /**
     * @param nodes
     * @return
     */
    private int countGateways(List<ProcessNode> nodes) {
        int _result = 0;
        for (ProcessNode n : nodes) {
            if (n instanceof Gateway) {
                _result++;
            }
        }
        return _result;
    }
    // End of variables declaration//GEN-END:variables

    public void loadText(File file) {
        f_lastFile = file;
        WaitDialog _wd = new WaitDialog(this, true);
        LoaderThread _lt = new LoaderThread(file, _wd);
        _lt.start();
        _wd.setVisible(true);
    }

    public void processNodeEditingStarted(ProcessNode o, JTextField textfield) {
    }

    public void processNodeEditingFinished(ProcessNode o) {
    }

    public void processObjectClicked(ProcessObject o) {
        f_processor.textModelElementClicked(o);
        f_editor.repaint();
    }

    public void processObjectDoubleClicked(ProcessObject o) {
    }

    public void modelChanged(ProcessModel m) {
    }

    public void processObjectDragged(Dragable o, int oldX, int oldY) {
    }

    /**
     * @param wb
     */
    public void setWorkbench(Workbench wb) {
        f_mainWorkbench = wb;
        f_lsoControler = new LaneSplitOffControler(wb);
        f_processor.setLaneSplitOffContoler(f_lsoControler);
    }

    @Override
    public void textModelChanged(TextModel model) {
        f_editor.setModel(model);
        f_editor.repaint();
    }

    @Override
    public void modelGenerated(BPMNModel model) {
        f_generatedModel = model;
        f_mainWorkbench.addModel(model.getProcessName(), model);
        if (f_processEditor != null) f_processEditor.removeListener(this);
        f_processEditor = f_mainWorkbench.getSelectedProcessEditor();
        f_processEditor.addListener(this);
    }

    @Override
    public void displayTree(Tree tree) {
        f_treePanel.setTree(tree);
    }

    @Override
    public void displayDependencies(String string) {
        f_textPaneDependencies.setText(string);
    }

    /**
     * is called when an element of an action was clicked.
     *
     * @param element - the element which belongs to the text node which was clicked
     * @param corr    - the corresponding node in the generated process model
     */
    @Override
    public void textElementClicked(SpecifiedElement element, FlowObject corr) {
        f_mainWorkbench.getSelectedProcessEditor();
        if (f_highlightNode != null)
            f_highlightNode.setHighlighted(false);
        f_highlightNode = corr;
        corr.setHighlighted(true);
        Rectangle _r = corr.getBounds();
        _r.width += 300;
        _r.height += 150;
        f_mainWorkbench.getSelectedProcessEditor().scrollRectToVisible(_r);
    }

    private class LoaderThread extends Thread implements ITextParsingStatusListener {

        private File f_file;
        private WaitDialog f_wd;
        private int f_max;

        /**
         *
         */
        public LoaderThread(File f, WaitDialog wd) {
            f_file = f;
            f_wd = wd;
        }

        @Override
        public void run() {
            try {
                if (f_file.exists()) {
                    long _start = System.currentTimeMillis();
                    f_wd.setText("Parsing...", "Parsing text...");
                    f_processor.parseText(f_file, this);
                    System.out.println("Text parsed in: " + (System.currentTimeMillis() - _start) + " ms");
                } else {
                    JOptionPane.showMessageDialog(f_wd, "Could not find file: " + f_file.getPath());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                f_wd.setVisible(false);
            }
        }

        @Override
        public void sentenceParsed(int number) {
            f_wd.setText("Parsing...", "Parsing sentence " + number + "/" + f_max);
            f_wd.setProgress(number);
        }

        @Override
        public void setNumberOfSentences(int number) {
            f_max = number;
            f_wd.setMaximum(number);
        }

    }

}
