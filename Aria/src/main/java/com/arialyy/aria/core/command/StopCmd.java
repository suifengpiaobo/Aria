/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arialyy.aria.core.command;

import android.text.TextUtils;
import android.util.Log;
import com.arialyy.aria.core.inf.IEntity;
import com.arialyy.aria.core.inf.ITask;
import com.arialyy.aria.core.inf.ITaskEntity;

/**
 * Created by lyy on 2016/9/20.
 * 停止命令
 */
class StopCmd<T extends ITaskEntity> extends AbsCmd<T> {

  StopCmd(String targetName, T entity) {
    super(targetName, entity);
  }

  @Override public void executeCmd() {
    if (!cancelExe) return;
    ITask task = mQueue.getTask(mEntity.getEntity());
    if (task == null) {
      if (mEntity.getEntity().getState() == IEntity.STATE_RUNNING) {
        task = mQueue.createTask(mTargetName, mEntity);
        mQueue.stopTask(task);
      } else {
        Log.w(TAG, "停止命令执行失败，【调度器中没有该任务】");
      }
    } else {
      if (!TextUtils.isEmpty(mTargetName)) {
        task.setTargetName(mTargetName);
      }
      mQueue.stopTask(task);
    }
  }
}