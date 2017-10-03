package com.ao.java;

public interface TemplateMvpRecycler {


    String mvp =
            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp.IMvp;\n\n" +

                    "interface I%2$sMvp {\n" +
                    "   interface I%2$sModel extends IMvp.IModel {}\n" +
                    "   interface I%2$sPresenter extends IMvp.IPresenter {\n" +
                    "       %3$s" +
                    "   }\n" +
                    "   interface I%2$sView extends IMvp.IView {}\n" +
                    "}";

    String presenter_activity =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.support.v7.app.AppCompatActivity;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private AppCompatActivity mActivity;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +
                    "   %3$s\n" +

                    "   %2$sPresenterImpl(AppCompatActivity activity,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = activity;\n" +
                    "       mActivity = activity;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +
                    "   %4$s\n" +
                    "}\n";


    String presenter_fragment =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.app.Fragment;\n" +

                    "import com.ao.framework.mvp.MvpPresenterImpl;\n\n" +

                    "class %2$sPresenterImpl extends MvpPresenterImpl implements I%2$sMvp.I%2$sPresenter{\n\n" +

                    "   private Context mContext;\n" +
                    "   private Fragment mFragment;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n\n" +
                    "   %3$s\n" +

                    "   %2$sPresenterImpl(Fragment fragment,I%2$sMvp.I%2$sView view){\n" +
                    "       super(view);\n" +
                    "       mContext = fragment.getContext();\n" +
                    "       mFragment = fragment;\n" +
                    "       mView = view;\n" +
                    "       mModel = new %2$sModelImpl(mContext);\n" +
                    "//       setBusCallback(BusCode.MAIN);\n" +
                    "   }\n\n" +
                    "   %4$s\n" +
                    "}\n";

    String presenter_recycler =
            "@Override\n" +
                    "   public %1$sRecyclerPresenter get%1$sRecyclerPresenter(%1$sRecyclerAdapter adapter) {\n" +
                    "       m%1$sRecyclerPresenter = new %1$sRecyclerPresenter(this, mModel, mView, adapter);\n" +
                    "       adapter.withPresenter(this, m%1$sRecyclerPresenter);\n" +
                    "       return m%1$sRecyclerPresenter;\n" +
                    "   }\n";


    String model =
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


    String recycler_presenter =

            "package %1$s;\n\n" +

                    "import com.ao.framework.mvp_recycler.AbsRecyclerPresenter;\n" +
                    "import com.ao.framework.mvp_recycler.DataSet;\n\n" +

                    "class %3$sRecyclerPresenter extends AbsRecyclerPresenter<Object> {\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n" +
                    "   private I%2$sMvp.I%2$sModel mModel;\n" +
                    "   private I%2$sMvp.I%2$sView mView;\n" +

                    "   %3$sRecyclerPresenter(I%2$sMvp.I%2$sPresenter presenter, I%2$sMvp.I%2$sModel model, I%2$sMvp.I%2$sView view, %3$sRecyclerAdapter adapter) {\n" +
                    "       super(presenter, adapter, adapter);\n" +
                    "       //getDataSet().enableEmpty(false);\n" +
                    "       //getDataSet().enableMore(DataSet.MORE_NONE);\n" +
                    "       mModel = model;\n" +
                    "       mPresenter = presenter;\n" +
                    "       mView = view;\n" +
                    "       adapter.withPresenter(presenter,this);\n" +
                    "   }\n" +
                    "}\n";

    String adpter =
            "package %1$s;\n\n" +

                    "import android.content.Context;\n" +
                    "import android.support.annotation.NonNull;\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout;\n" +
                    "import android.support.v7.widget.RecyclerView;\n" +
                    "import android.view.LayoutInflater;\n" +
                    "import android.view.View;\n" +
                    "import android.view.ViewGroup;\n\n" +

                    "import com.ao.framework.mvp_recycler.AbsRecyclerAdapter;\n" +
                    "import com.ao.framework.mvp_recycler.DataSet;\n" +
                    "import com.ao.framework.mvp_recycler.VHEmptyData;\n" +
                    "import com.ao.framework.mvp_recycler.VHEmptyError;\n" +
                    "import com.ao.framework.mvp_recycler.VHMoreEnd;\n" +
                    "import com.ao.framework.mvp_recycler.VHMoreError;\n" +
                    "import com.ao.framework.mvp_recycler.VHMoreLoading;\n" +
                    "import com.ao.framework.mvp_recycler.VHNull;\n\n" +

                    "class %3$sRecyclerAdapter extends AbsRecyclerAdapter {\n\n" +
                    "   private Context mContext;\n" +
                    "   private LayoutInflater mInflater;\n" +
                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n" +
                    "   private %3$sRecyclerPresenter mRecyclerPresenter;\n\n" +

                    "   %3$sRecyclerAdapter(Context context, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {\n" +
                    "       super(context, recyclerView, swipeRefreshLayout);\n" +
                    "       mInflater = LayoutInflater.from(context);\n" +
                    "   }\n\n" +

                    "   void withPresenter(I%2$sMvp.I%2$sPresenter presenter, %3$sRecyclerPresenter recyclerPresenter) {\n" +
                    "       mPresenter = presenter;\n" +
                    "       mRecyclerPresenter = recyclerPresenter;\n" +
                    "       bindAdapter();\n" +
                    "   }\n\n" +

                    "   @NonNull\n@Override\n" +
                    "   public %3$sRecyclerPresenter getRecyclerPresenter() {\n" +
                    "       return mRecyclerPresenter;\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {\n" +
                    "       if (viewType == DataSet.Type.EMPTY) {\n" +
                    "           return new VHEmptyData(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.EMPTY_ERROR) {\n" +
                    "           return new VHEmptyError(mContext,mRecyclerPresenter);\n" +
                    "       } else if (viewType == DataSet.Type.MORE_LOADING) {\n" +
                    "           return new VHMoreLoading(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.MORE_END) {\n" +
                    "           return new VHMoreEnd(mContext);\n" +
                    "       } else if (viewType == DataSet.Type.MORE_ERROR) {\n" +
                    "           return new VHMoreError(mContext,mRecyclerPresenter);\n" +
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

    String view_activity =
            "package %1$s;\n\n" +

                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout;\n" +
                    "import android.support.v7.widget.RecyclerView;\n\n" +

                    "import com.ao.framework.mvp.MvpActivity;\n\n" +

                    "public class %2$sActivity extends MvpActivity implements I%2$sMvp.I%2$sView {\n\n" +

                    "   private SwipeRefreshLayout mSwipeRefreshLayout;\n" +
                    "   private RecyclerView mRecyclerView;\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n" +
                    "   %3$s\n" +

                    "   @Override\n" +
                    "   protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                    "       super.onCreate(savedInstanceState);\n" +
                    "//        setContentView(R.layout.);\n" +
                    "       initViewAndPresenter();\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected void initViewAndPresenter() {\n" +
                    "       //mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);\n" +
                    "       //mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);\n\n" +

                    "       mPresenter = new %2$sPresenterImpl(this, this);\n" +
                    "       %4$s\n\n" +

                    "   }\n" +
                    "}";

    String view_fragment =
            "package %1$s;\n\n" +

                    "import android.os.Bundle;\n" +
                    "import android.support.annotation.Nullable;\n" +
                    "import android.support.v4.widget.SwipeRefreshLayout;\n" +
                    "import android.support.v7.widget.RecyclerView;\n" +
                    "import android.view.LayoutInflater;\n" +
                    "import android.view.View;\n\n" +
                    "import android.view.ViewGroup;\n" +

                    "import com.ao.framework.mvp.MvpFragment;\n\n" +

                    "public class %2$sFragment extends MvpFragment implements I%2$sMvp.I%2$sView {\n\n" +

                    "   private SwipeRefreshLayout mSwipeRefreshLayout;\n" +
                    "   private RecyclerView mRecyclerView;\n\n" +

                    "   private I%2$sMvp.I%2$sPresenter mPresenter;\n" +
                    "   %3$s\n" +

                    "   @Nullable\n" +
                    "   @Override\n" +
                    "   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {\n" +
                    "       View view = inflater.inflate(0, null);\n" +
                    "       return initViewAndPresenter(view);\n" +
                    "   }\n\n" +

                    "   @Override\n" +
                    "   protected View initViewAndPresenter(View v) {\n" +
                    "       //mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);\n" +
                    "       //mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);\n\n" +

                    "       mPresenter = new %2$sPresenterImpl(this, this);\n" +
                    "       %4$s\n\n" +

                    "       return v;\n" +
                    "   }\n" +
                    "}";

}
