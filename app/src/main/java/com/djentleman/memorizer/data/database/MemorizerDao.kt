package com.djentleman.memorizer.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.models.NoteStatus

@Dao
interface MemorizerDao {

    @Query("SELECT * FROM notes WHERE noteStatus =:status")
    fun getNotesList(status: NoteStatus = NoteStatus.ACTUAL): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE noteStatus = :status")
    fun getArchivedList(status: NoteStatus = NoteStatus.ARCHIVED): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE noteStatus = :status")
    fun getTrashList(status: NoteStatus = NoteStatus.TRASHED): LiveData<List<NoteDbModel>>


    //TODO
    // other queries


//    @Query("SELECT * FROM shop_items")
//    fun getShopList(): LiveData<List<ShopItemDbModel>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)
//
//    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
//    suspend fun deleteShopItem(shopItemId: Int)
//
//    @Query("SELECT * FROM shop_items WHERE id=:shopItemId LIMIT 1")
//    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel
}