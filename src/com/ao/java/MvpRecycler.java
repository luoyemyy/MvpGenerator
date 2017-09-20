package com.ao.java;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MvpRecycler extends AnAction {
    private String ACTIVITY = "Activity.java";
    private String FRAGMENT = "Fragment.java";
    private String VIEW = "View.java";

    private List<String> domain = Arrays.asList("com", "cn", "net");

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (project == null || editor == null) {
            return;
        }
        PsiFile currentEditorFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        if (currentEditorFile == null) {
            return;
        }

        String currentEditorFileName = currentEditorFile.getName();
        String modelName = currentEditorFileName;
        if (currentEditorFileName.endsWith(ACTIVITY)) {
            modelName = currentEditorFileName.replace(ACTIVITY, "");
        } else if (currentEditorFileName.endsWith(FRAGMENT)) {
            modelName = currentEditorFileName.replace(FRAGMENT, "");
        } else if (currentEditorFileName.endsWith(VIEW)) {
            modelName = currentEditorFileName.replace(VIEW, "");
        }

        PsiDirectory directory = currentEditorFile.getParent();


        List<String> list = new ArrayList<>();
        while (directory != null) {
            list.add(directory.getName());
            if (domain.contains(directory.getName())) {
                break;
            }
            directory = directory.getParent();
        }
        if (list.size() == 0) {
            return;
        }
        Collections.reverse(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            if (i < list.size() - 1) {
                stringBuilder.append(".");
            }
        }
        String basePackage = stringBuilder.toString();

        String basePath = getCurrentPath(e);

        try {
            createPresenterClass(basePackage, basePath, modelName);
            createModelClass(basePackage, basePath, modelName);
            createMvpClass(basePackage, basePath, modelName);
        } catch (IOException e1) {
            Messages.showMessageDialog("create file failed", "Error", Messages.getErrorIcon());
            return;
        }
//        Messages.showMessageDialog("created success! please wait a moment", "Success", Messages.getInformationIcon());
        refreshProject(e);
    }


    private void refreshProject(AnActionEvent e) {
        e.getProject().getBaseDir().refresh(false, true);
    }

    private void createMvpClass(String basePackage, String path, String modelName) throws IOException {
        String filename = "I" + modelName + "Mvp.java";
        File file = new File(path, filename);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(MvpRecyclerTemplate.MVP_TEMPLATE, basePackage, modelName);
        writer.write(content);
        writer.flush();
        writer.close();
    }

    private void createModelClass(String basePackage, String path, String modelName) throws IOException {
        String filename = modelName + "ModelImpl.java";
        File file = new File(path, filename);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(MvpRecyclerTemplate.MODEL_TEMPLATE, basePackage, modelName);
        writer.write(content);
        writer.flush();
        writer.close();
    }

    private void createPresenterClass(String basePackage, String path, String modelName) throws IOException {
        String filename = modelName + "PresenterImpl.java";
        File file = new File(path, filename);
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String content = String.format(MvpRecyclerTemplate.PRESENTER_TEMPLATE, basePackage, modelName);
        writer.write(content);
        writer.flush();
        writer.close();
    }


    private String getCurrentPath(AnActionEvent e) {
        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        if (currentFile != null) {
            return currentFile.getParent().getPath();
        }
        return null;
    }

}
