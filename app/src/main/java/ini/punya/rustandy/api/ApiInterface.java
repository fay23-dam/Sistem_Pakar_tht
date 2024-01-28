package ini.punya.rustandy.api;



import java.util.List;

import ini.punya.rustandy.model.HasilModul;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("bayes.php")
    Call<List<HasilModul>> gethasil(
            @Query("key") String key
    );
}
