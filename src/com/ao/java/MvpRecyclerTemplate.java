package com.ao.java;

interface MvpRecyclerTemplate {


    String MVP_TEMPLATE =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp.IMvp;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IMvp.IModel {}\n" +
                    "   interface I%2$sPresenter extends IMvp.IPresenter {\n" +
                    "       RecyclerPresenter getRecycler(RecyclerAdapter adapter);\n" +
                    "   }\n" +
                    "   interface I%2$sView extends IMvp.IView {}\n" +
                    "}";


    String PRESENTER_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.app.Activity;\n" +
                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Activity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n" +
                    "   private RecyclerPresenter mRecyclerPresenter;\n\n" +

                    "   %2$sPresenterImpl(Activity activity,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   public RecyclerPresenter getRecycler(RecyclerAdapter adapter) {\n" +
                    "       mRecyclerPresenter = new RecyclerPresenter(this, mModel, mView, adapter);\n" +
                    "       adapter.withPresenter(this, mRecyclerPresenter);\n" +
                    "       return mRecyclerPresenter;\n" +
                    "   }\n\n" +
                    "}";

    String MODEL_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n\n" +

                    "class %2$sModelImpl implements I%2$sMvp.I%2$sModel {\n\n" +

                    "   private Context mContext;\n" +
                    "//   private ApiService mApiService;\n\n" +

                    "   %2$sModelImpl(Context context){\n" +
                    "       mContext = context;\n" +
                    "//       mApiService = new ApiService(context);\n" +
                    "   }\n\n" +

                    "}";

    String RECYCLER_PRESENTER_TEMPLATE =

            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp_recycler.AbsRecyclerPresenter;\n" +
                    "import com.ao.framework.mvp_recycler.DataSet;\n\n" +

                    "class RecyclerPresenter extends AbsRecyclerPresenter<Object> {\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n" +

                    "   RecyclerPresenter(I%2$sMvp.I%2$sPresenter presenter, I%2$sMvp.I%2$sModel model, I%2$sMvp.I%2$sView view, RecyclerAdapter adapter) {\n" +
                    "       super(presenter, adapter, true, DataSet.MORE_AFTER, adapter);\n" +
                    "       mModel = model;\n" +
                    "       mPresenter = presenter;\n" +
                    "       mView = view;\n" +
                    "   }\n" +
                    "}\n";

    String ADAPTER_TEMPLATE =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout;\n" +
                    "import android.support.v7.widget.RecyclerView;\n" +
                    "import android.view.LayoutInflater;\n" +
                    "import android.view.View;\n" +
                    "import android.view.ViewGroup;\n\n" +

                    "import com.ao.framework.mvp_recycler.AbsRecyclerAdapter;\n" +
                    "import com.ao.framework.mvp_recycler.DataSet;\n" +
                    "import com.ao.framework.mvp_recycler.VHEmptyData;\n" +
                    "import com.ao.framework.mvp_recycler.VHMoreEnd;\n" +
                    "import com.ao.framework.mvp_recycler.VHMoreLoading;\n" +
                    "import com.ao.framework.mvp_recycler.VHNull;\n\n" +

                    "class RecyclerAdapter extends AbsRecyclerAdapter<Object> {\n\n" +
                    "   private Context mContext;\n" +
                    "   private LayoutInflater mInflater;\n" +
                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n\n"+

                    "   RecyclerAdapter(Context context, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {\n" +
                    "       super(context, recyclerView, swipeRefreshLayout);\n" +
                    "       mContext = context;\n" +
                    "       mInflater = LayoutInflater.from(context);\n" +
                    "   }\n\n" +

                    "   public void withPresenter(I%2$sMvp.I%2$sPresenter presenter, RecyclerPresenter recyclerPresenter) {\n" +
                    "       super.withPresenter(recyclerPresenter);\n" +
                    "       mPresenter = presenter;\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {\n" +
                    "       if (viewType == DataSet.Type.EMPTY) {\n" +
                    "           return new VHEmptyData(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.MORE_LOADING) {\n" +
                    "           return new VHMoreLoading(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.MORE_END) {\n" +
                    "           return new VHMoreEnd(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.CONTENT) {\n" +
                    "           return new VH(mInflater.inflate(0, parent, false));\n" +
                    "       }\n" +
                    "       return new VHNull(mContext);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {\n" +
                    "       int viewType = getItemViewType(position);\n" +
                    "       if (viewType == DataSet.Type.CONTENT) {\n" +
                    "       }\n" +
                    "   }\n\n" +

                    "   private class VH extends RecyclerView.ViewHolder {\n" +
                    "       VH(View itemView) {\n" +
                    "           super(itemView);\n" +
                    "       }\n" +
                    "   }\n\n" +

                    "}";
}