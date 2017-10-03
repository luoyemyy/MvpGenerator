package com.ao.java;

import a.d.e.a.S;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MvpRecycler extends Creator {

    private String recyclerName;
    private List<String> names;

    public MvpRecycler(String path, String packageName, boolean isActivity, String name, String recyclerName) {
        super(path, packageName, isActivity, name);
        this.recyclerName = recyclerName;
        names = new ArrayList<>();
        if (StringUtils.isEmpty(recyclerName)) {
            names.add("");
        } else {
            names.addAll(Arrays.asList(recyclerName.split(",")));
        }
    }

    public void create() {
        try {
            createMvpClass();
            createViewClass();
            createPresenterClass();
            createModelClass();
            names.forEach(n -> {
                try {
                    createRecyclerAdapterClass(n);
                    createRecyclerPresenterClass(n);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createViewClass() throws IOException {
        String filename = name + (isActivity ? "Activity.java" : "Fragment.java");

        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        names.forEach(n -> {
            sb3.append(String.format("private %1$sRecyclerPresenter m%1$sRecyclerPresenter;\n", n));
            sb4.append(String.format("%1$sRecyclerAdapter adapter%1$s = new %1$sRecyclerAdapter(%2$s, mRecyclerView, mSwipeRefreshLayout);\n" +
                    "m%1$sRecyclerPresenter = mPresenter.get%1$sRecyclerPresenter(adapter%1$s);\n", n, isActivity ? "this" : "getContext()"));
        });

        String content = String.format(isActivity ? TemplateMvpRecycler.view_activity : TemplateMvpRecycler.view_fragment, packageName, name, sb3.toString(), sb4.toString());
        write(filename, content);
    }

    private void createPresenterClass() throws IOException {
        String filename = name + "PresenterImpl.java";

        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        names.forEach(n -> {
            sb3.append(String.format("private %1$sRecyclerPresenter m%1$sRecyclerPresenter;\n", n));
            sb4.append(String.format(TemplateMvpRecycler.presenter_recycler, n));
        });

        String content = String.format(isActivity ? TemplateMvpRecycler.presenter_activity : TemplateMvpRecycler.presenter_fragment, packageName, name, sb3.toString(), sb4.toString());
        write(filename, content);
    }

    private void createMvpClass() throws IOException {
        String filename = "I" + name + "Mvp.java";

        StringBuilder sb3 = new StringBuilder();
        names.forEach(n -> sb3.append(String.format("%1$sRecyclerPresenter get%1$sRecyclerPresenter(%1$sRecyclerAdapter adapter);\n", n)));

        String content = String.format(TemplateMvpRecycler.mvp, packageName, name, sb3.toString());
        write(filename, content);
    }

    private void createModelClass() throws IOException {
        String filename = name + "ModelImpl.java";
        String content = String.format(TemplateMvpRecycler.model, packageName, name);
        write(filename, content);
    }

    private void createRecyclerPresenterClass(String n) throws IOException {
        String filename = n + "RecyclerPresenter.java";
        String content = String.format(TemplateMvpRecycler.recycler_presenter, packageName, name, n);
        write(filename, content);
    }

    private void createRecyclerAdapterClass(String n) throws IOException {
        String filename = n + "RecyclerAdapter.java";
        String content = String.format(TemplateMvpRecycler.adpter, packageName, name, n);
        write(filename, content);
    }
}
