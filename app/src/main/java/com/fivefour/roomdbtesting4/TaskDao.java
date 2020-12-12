package com.fivefour.roomdbtesting4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface TaskDao {

   // @Query(" SELECT * FROM Task ")
    //List<Task> getAll();

    @Insert
    Void insert(Task task);

//    @Delete
   // void delete(Task task);

    //@Update
    //void update(Task task);
}
