package th.ac.ssru.kaewruamchor;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import th.ac.ssru.kaewruamchor.Dao.AmphursDao;
import th.ac.ssru.kaewruamchor.Dao.AuthDao;
import th.ac.ssru.kaewruamchor.Dao.DistrictsDao;
import th.ac.ssru.kaewruamchor.Dao.EducationListDao;
import th.ac.ssru.kaewruamchor.Dao.GetCertsDao;
import th.ac.ssru.kaewruamchor.Dao.GetFriendsLocationDao;
import th.ac.ssru.kaewruamchor.Dao.GetFriendsPhotoDao;
import th.ac.ssru.kaewruamchor.Dao.GetStudentDao;
import th.ac.ssru.kaewruamchor.Dao.ProvincesDao;
import th.ac.ssru.kaewruamchor.Dao.UpdateDao;

/**
 * Created by Hermione on 7/5/2017.
 */

public interface ApiService {







    @POST("getprovinces.php")
    Call<ProvincesDao> province();

    @POST("getamphurs.php")
    @FormUrlEncoded
    Call<AmphursDao> amphur(@Field("id") String id);

    @POST("getdistricts.php")
    @FormUrlEncoded
    Call<DistrictsDao> district(@Field("id") String id);



    //API for authentication
    @POST("auth.php")
    @FormUrlEncoded
    Call<AuthDao> auth(@Field("fname") String fname,
                       @Field("lname") String lname,
                       @Field("birthday") String birthday);

    //API for fetch alumni data
    @POST("getstudent.php")
    @FormUrlEncoded
    Call<GetStudentDao> getStudent(@Field("id") String id);


    @POST("listedu.php")
    @FormUrlEncoded
    Call<GetCertsDao> getCert(@Field("id") String id);

    //API for update alumni data
    @POST("update.php")
    @FormUrlEncoded
    Call<UpdateDao> update(@Field("id") String id,
                           @Field("prefix") String prefix,
                           @Field("engfname") String engfname,
                           @Field("englname") String englname,
                           @Field("job") String job,
                           @Field("status") String status,
                           @Field("email") String email,
                           @Field("phonenumber") String phonenumber,
                           @Field("facebook") String facebook,
                           @Field("line") String line,
                           @Field("housenumber") String housenumber,
                           @Field("moo") String moo,
                           @Field("alley") String alley,
                           @Field("street") String street,
                           @Field("district") String district,
                           @Field("amphur") String amphur,
                           @Field("province") String province,
                           @Field("zipcode") String zipcode);


    //API for update alumni data
    @POST("update.php")
    @FormUrlEncoded
    Call<UpdateDao> updateLocation(@Field("id") String id,
                           @Field("latitude") double latitude,
                           @Field("longtitude") double longtitude);

    @POST("getfriendslocation.php")
    @FormUrlEncoded
    Call<GetFriendsLocationDao> location(@Field("facultyid") String facultyid,
                                         @Field("majorid") String majorid,
                                         @Field("levelid") String levelid,
                                         @Field("catid") String catid,
                                         @Field("sec") String sec,
                                         @Field("year") String year);

    @POST("getfriendsphoto.php")
    @FormUrlEncoded
    Call<GetFriendsPhotoDao> photo(@Field("facultyid") String facultyid,
                                      @Field("majorid") String majorid,
                                      @Field("levelid") String levelid,
                                      @Field("catid") String catid,
                                      @Field("sec") String sec,
                                      @Field("year") String year);



    @POST("listedu.php")
    @FormUrlEncoded
    Call<EducationListDao> getEducation(@Field("id") String id);
    @Multipart
    @POST("upload.php")
    Call<String> upload(@Part MultipartBody.Part image,
                        @Part("id") RequestBody id);
}