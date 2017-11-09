package com.ao.kotlin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Arrays;
import java.util.List;

public class Generator extends AnAction implements InputDialog.Callback {

    private VirtualFile virtualFile;

    private String path;
    private String packageName;
    private boolean isActivity;
    private boolean isRecycler;
    private String name;
    private String recyclerName;

    private List<String> rootPath = Arrays.asList("java", "src");

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        virtualFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (virtualFile == null) {
            return;
        }
        path = virtualFile.getPath();
        packageName = virtualFile.getName();
        getPackage(virtualFile);

        new InputDialog(project, this).show();

    }

    private void getPackage(VirtualFile virtualFile) {
        VirtualFile parent = virtualFile.getParent();
        if (parent != null && parent.exists()) {
            String name = parent.getName();
            if (!rootPath.contains(name)) {
                packageName = parent.getName() + "." + packageName;
                getPackage(parent);
            }
        }
    }

    @Override
    public void ok(boolean activity, boolean recycler, String name, String recyclerName) {
        isActivity = activity;
        isRecycler = recycler;
        this.name = name;
        this.recyclerName = recyclerName;
        createFiles();

        virtualFile.refresh(true, true);
    }

    private void createFiles() {

        if (isRecycler) {
            new MvpRecycler(path, packageName, isActivity, name, recyclerName).create();
        } else {
            new Mvp(path, packageName, isActivity, name).create();
        }
    }

}
