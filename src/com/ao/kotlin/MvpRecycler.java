package com.ao.kotlin;

import org.apache.commons.lang.StringUtils;

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
        String filename = name + (isActivity ? "Activity.kt" : "Fragment.kt");

        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        names.forEach(n -> {
            sb3.append(String.format("private lateinit var m%1$sRecyclerPresenter: %1$sRecyclerPresenter\n", n));
            sb4.append(String.format("m%1$sRecyclerPresenter = mPresenter.get%1$sRecyclerPresenter(%1$sRecyclerAdapter(%2$s, mRecyclerView, mSwipeRefreshLayout))\n", n, isActivity ? "this" : "getContext()"));
        });

        String content = String.format(isActivity ? TemplateMvpRecycler.view_activity : TemplateMvpRecycler.view_fragment, packageName, name, sb3.toString(), sb4.toString());
        write(filename, content);
    }

    private void createPresenterClass() throws IOException {
        String filename = name + "PresenterImpl.kt";

        StringBuilder sb3 = new StringBuilder();
        StringBuilder sb4 = new StringBuilder();
        names.forEach(n -> {
            sb3.append(String.format("private lateinit var m%1$sRecyclerPresenter: %1$sRecyclerPresenter\n\n", n));
            sb4.append(String.format(TemplateMvpRecycler.presenter_recycler, n));
        });

        String content = String.format(isActivity ? TemplateMvpRecycler.presenter_activity : TemplateMvpRecycler.presenter_fragment, packageName, name, sb3.toString(), sb4.toString());
        write(filename, content);
    }

    private void createMvpClass() throws IOException {
        String filename = "I" + name + "Mvp.kt";

        StringBuilder sb3 = new StringBuilder();
        names.forEach(n -> sb3.append(String.format("fun get%1$sRecyclerPresenter(adapter: %1$sRecyclerAdapter): %1$sRecyclerPresenter\n", n)));

        String content = String.format(TemplateMvpRecycler.mvp, packageName, name, sb3.toString());
        write(filename, content);
    }

    private void createModelClass() throws IOException {
        String filename = name + "ModelImpl.kt";
        String content = String.format(TemplateMvpRecycler.model, packageName, name);
        write(filename, content);
    }

    private void createRecyclerPresenterClass(String n) throws IOException {
        String filename = n + "RecyclerPresenter.kt";
        String content = String.format(TemplateMvpRecycler.recycler_presenter, packageName, name, n, n.equals("") ? "Any" : n);
        write(filename, content);
    }

    private void createRecyclerAdapterClass(String n) throws IOException {
        String filename = n + "RecyclerAdapter.kt";
        String content = String.format(TemplateMvpRecycler.adapter, packageName, name, n);
        write(filename, content);
    }
}
