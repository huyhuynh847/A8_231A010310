
package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class NewsDetailFragment extends Fragment {

    public static final String KEY_TIEU_DE = "key_tieu_de";
    private static final String KEY_NOI_DUNG = "key_noi_dung";

    public static NewsDetailFragment newInstance(
            String tieuDe, String noiDung) {

        NewsDetailFragment f = new NewsDetailFragment();

        Bundle args = new Bundle();
        args.putString(KEY_TIEU_DE, tieuDe);
        args.putString(KEY_NOI_DUNG, noiDung);

        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Bundle args = requireArguments();

        String tieuDe = args.getString(KEY_TIEU_DE, "");
        String noiDung = args.getString(KEY_NOI_DUNG, "");

        TextView tvTieuDe = view.findViewById(R.id.tvTieuDe);
        TextView tvNoiDung = view.findViewById(R.id.tvNoiDung);
        Button btnDaDoc = view.findViewById(R.id.btnDaDoc);

        tvTieuDe.setText(tieuDe);
        tvNoiDung.setText(noiDung);

        btnDaDoc.setOnClickListener(v -> {

            // Lưu trạng thái tin đã đọc vào ViewModel dùng chung
            A8ViewModel viewModel =
                    new ViewModelProvider(requireActivity())
                            .get(A8ViewModel.class);

            viewModel.setTinDaDoc(tieuDe);

            // Gửi kết quả về NewsListFragment
            Bundle kq = new Bundle();
            kq.putString(KEY_TIEU_DE, tieuDe);

            getParentFragmentManager().setFragmentResult(
                    NewsListFragment.REQUEST_KEY, kq);

            // Tablet: giữ chi tiết ở khung bên phải
            if (requireActivity().findViewById(
                    R.id.detailContainer) == null) {

                // Điện thoại: quay lại danh sách
                getParentFragmentManager().popBackStack();
            }
        });
    }
}
