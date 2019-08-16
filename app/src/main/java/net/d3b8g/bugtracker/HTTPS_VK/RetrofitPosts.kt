package net.d3b8g.bugtracker.HTTPS_VK

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface RetrofitPosts {
    @GET("posts")
    fun getPosts(): Deferred<retrofit2.Response<List<Post>>>
}

data class Post(val id:Int,val title:String)

abstract class BaseRepository<Params,Result> {
    abstract suspend fun doWork(params:Params) : Result
}

class PostRepositry
    : BaseRepository<PostRepositry.Params, PostRepositry.Result>(){

    override suspend fun doWork(params: Params): Result {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RetrofitPosts::class.java)
        val result = retrofit
            .getPosts()
            .await()

        return Result(result.body())
    }

    class Params
    data class Result(val posts:List<Post>?)

}

abstract class BaseUseCan<Params,Result>{
    abstract suspend fun doWork(params:Params): Result
}

class GetListOfPostsUseCan
    :BaseUseCan<GetListOfPostsUseCan.Params,GetListOfPostsUseCan.Result>(){
    override suspend fun doWork(params: Params): Result {
        return Result(
            PostRepositry()
                .doWork(PostRepositry.Params())
                .posts
        )
    }

    class Params

    data class Result(val posts: List<Post>?)

}
