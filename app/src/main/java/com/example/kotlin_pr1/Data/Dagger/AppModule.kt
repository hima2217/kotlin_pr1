package com.example.kotlin_pr1.Data.Dagger

import android.app.Application
import com.example.kotlin_pr1.Data.DataSource.Product.ProductDao
import com.example.kotlin_pr1.Data.DataSource.Product.ProductDatabase
import com.example.kotlin_pr1.Data.DataSource.Product.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideProductDatabase(): ProductDatabase {
        return ProductDatabase.getDatabase(application)
    }

    @Provides
    @Singleton
    fun provideProductDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.productDao()
    }

    @Provides
    @Singleton
    fun provideProductRepository(productDao: ProductDao): ProductRepository {
        return ProductRepository(productDao)
    }
}