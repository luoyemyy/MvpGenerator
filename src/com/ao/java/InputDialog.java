package com.ao.java;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class InputDialog extends DialogWrapper {

    private JTextField nameInput;
    private JRadioButton activity;
    private JCheckBox recycler;
    private JTextField recyclerInput;

    private Callback callback;

    InputDialog(@Nullable Project project, Callback callback) {
        super(project);
        this.callback = callback;
        init();
        setTitle("Generator");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(createName());
        panel.add(createType());
        panel.add(createRecycler());
        panel.add(createRecyclerName());

        return panel;
    }

    @Override
    protected void doOKAction() {
        String name = nameInput.getText();
        if (StringUtils.isNotEmpty(name)) {
            callback.ok(activity.getModel().isSelected(), recycler.isSelected(), name,recyclerInput.getText());
            super.doOKAction();
        }
    }

    private JPanel createName() {
        JPanel jPanel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("名称：");
        label.setPreferredSize(new Dimension(50, 30));
        jPanel.add(label);

        nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(300, 25));
        jPanel.add(nameInput);

        return jPanel;
    }

    private JPanel createType() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("类型：");
        label.setPreferredSize(new Dimension(50, 30));
        jPanel.add(label);

        activity = new JRadioButton("Activity");
        JRadioButton fragment = new JRadioButton("Fragment");

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(activity);
        typeGroup.add(fragment);
        activity.getModel().setSelected(true);

        jPanel.add(activity);
        jPanel.add(fragment);

        return jPanel;
    }

    private JPanel createRecycler() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("列表：");
        label.setPreferredSize(new Dimension(50, 30));
        jPanel.add(label);

        recycler = new JCheckBox();
        jPanel.add(recycler);

        return jPanel;
    }

    private JPanel createRecyclerName() {
        JPanel jPanel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("实体：");
        label.setPreferredSize(new Dimension(50, 30));
        jPanel.add(label);

        recyclerInput = new JTextField();
        recyclerInput.setPreferredSize(new Dimension(300, 25));
        jPanel.add(recyclerInput);

        return jPanel;
    }


    public interface Callback {
        void ok(boolean activity, boolean recycler, String name,String recyclerName);
    }

}
