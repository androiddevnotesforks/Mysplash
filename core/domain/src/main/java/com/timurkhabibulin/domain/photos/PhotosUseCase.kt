package com.timurkhabibulin.domain.photos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.timurkhabibulin.domain.ImageDownloader
import com.timurkhabibulin.domain.ItemsPagingSource
import com.timurkhabibulin.domain.entities.Photo
import com.timurkhabibulin.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

private const val NETWORK_PAGE_SIZE = 10

class PhotosUseCase @Inject constructor(
    private val photosRepository: PhotosRepository,
    private val dispatcher: CoroutineDispatcher,
    private val imageDownloader: ImageDownloader
) {

    fun getPhotos(): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                ItemsPagingSource { page ->
                    photosRepository.getPhotos(page)
                }
            }
        ).flow.flowOn(dispatcher)
    }

    suspend fun getPhoto(id: String): Result<Photo> = photosRepository.getPhoto(id)

    suspend fun trackDownload(id: String) {
        photosRepository.trackDownload(id)
    }

    suspend fun downloadPhoto(url: String): Result<Photo> {
        return photosRepository.downloadPhoto(url)
    }

     fun savePhoto(fileName: String, url: String?, width: Int, height: Int): Boolean {
        imageDownloader.download(fileName, url, width, height)
        return true //TODO
    }
}
