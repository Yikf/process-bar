/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icafe.command;

public interface ProcessBar {

  /**
   * Currently process. This method will be called every second to get the current progress.
   */
  int process();

  /**
   * The total length of the process bar.
   *
   * Note: It is called only once at starting process bar, It usually doesn't change.
   */
  int total();

}
