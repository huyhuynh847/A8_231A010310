package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsListFragment extends Fragment {
    public static final String REQUEST_KEY = "news_result";
    private TextView tvDaDoc;
    private LinearLayout boxTin;

    public NewsListFragment() {
        super(R.layout.fragment_news_list);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDaDoc = view.findViewById(R.id.tvDaDoc);
        boxTin = view.findViewById(R.id.boxTin);

        Button btnNews1 = view.findViewById(R.id.btnNews1);
        Button btnNews2 = view.findViewById(R.id.btnNews2);

        btnNews1.setOnClickListener(v ->
                moChiTiet("Thông báo học vụ VHU"));

        btnNews2.setOnClickListener(v ->
                moChiTiet("Hoạt động sinh viên"));

        capNhatTinDaDoc();
    }

    private void moChiTiet(String tieuDe) {
        Bundle args = new Bundle();
        args.putString("tieu_de", tieuDe);

        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack("chi_tiet")
                .commit();
    }

    private void capNhatTinDaDoc() {
        if (tvDaDoc != null) {
            tvDaDoc.setText(R.string.list_last_read);
        }

        if (boxTin != null) {
            boxTin.setVisibility(View.VISIBLE);
        }
    }
}
