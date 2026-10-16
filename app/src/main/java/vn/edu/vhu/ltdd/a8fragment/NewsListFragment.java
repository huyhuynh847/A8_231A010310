
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
    private String tinDaDoc = "";

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

        // Nhận kết quả từ màn hình chi tiết
        getParentFragmentManager().setFragmentResultListener(
                REQUEST_KEY,
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    tinDaDoc = result.getString(
                            NewsDetailFragment.KEY_TIEU_DE, "");

                    capNhatTinDaDoc();
                }
        );

        // Mở tin thứ nhất
        btnNews1.setOnClickListener(v ->
                moChiTiet(
                        "Thông báo học vụ VHU",
                        "Thông tin về lịch học, lịch thi và các thông báo học vụ của trường VHU."
                )
        );

        // Mở tin thứ hai
        btnNews2.setOnClickListener(v ->
                moChiTiet(
                        "Hoạt động sinh viên",
                        "Thông tin về các hoạt động, phong trào và sự kiện dành cho sinh viên."
                )
        );

        capNhatTinDaDoc();
    }

    // Mở màn hình chi tiết và thêm vào Back Stack
    private void moChiTiet(String tieuDe, String noiDung) {
        NewsDetailFragment fragment =
                NewsDetailFragment.newInstance(tieuDe, noiDung);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack("chi_tiet")
                .commit();
    }

    // Cập nhật trạng thái tin đã đọc
    private void capNhatTinDaDoc() {
        if (tvDaDoc != null) {
            if (tinDaDoc.isEmpty()) {
                tvDaDoc.setText("Chưa đọc tin nào");
            } else {
                tvDaDoc.setText("Tin đã đọc: " + tinDaDoc);
            }
        }

        if (boxTin != null) {
            boxTin.setVisibility(View.VISIBLE);
        }
    }
}
