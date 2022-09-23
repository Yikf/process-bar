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

import java.lang.reflect.Constructor;
import java.text.NumberFormat;

public class ProcessBarUtils {

  private static final String ANSI_GREEN = "\u001B[32m";

  private static final String ANSI_RESET = "\u001B[0m";

  private static final long SECOND_TO_MILLIONS = 1000;

  public static ProcessBar supply(String sourceClass) {
    try {
      Class<?> aClass = Class.forName(sourceClass, false, ProcessBarUtils.class.getClassLoader());

      Constructor<?> constructor = aClass.getDeclaredConstructors()[0];
      return (ProcessBar) constructor.newInstance();
    } catch (Throwable t) {
      throw new RuntimeException("Invalid sc options, It must implement ProcessBar and have one and only one empty parameter constructor");
    }
  }

  public static void render(String prefix, String suffix, String format, int interval, ProcessBar processBar) {

    int total = processBar.total();

    // Print prefix
    System.out.print(prefix);

    int process = 0;
    int back = 0;

    // continues print until 100%
    while (process < total) {

      back(back);

      int cur = Math.min(processBar.process(), total);
      print(cur, format);

      printBlank(total - cur);

      // suffix
      System.out.print(suffix + ": ");

      float v = (float) cur / total;
      String per = process(v);
      System.out.print(ANSI_GREEN + per + ANSI_RESET);
      back = per.length() + suffix.length() + 2 + total;

      if (cur >= total) break;
      process = cur;
      waitInterval(interval);
    }
  }

  private static void waitInterval(int interval) {
    try {
      Thread.sleep(interval * SECOND_TO_MILLIONS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static String process(float n) {
    if (n >= 1) {
      return "100%";
    }

    NumberFormat num = NumberFormat.getPercentInstance();
    num.setMaximumIntegerDigits(3);
    num.setMaximumFractionDigits(2);
    return num.format(n);
  }

  private static void suffix(float n, String suffix) {
    System.out.print(suffix + ": ");
    if (n >= 1) {
      System.out.print("100%");
    }
  }

  private static void print(int n, String format) {
    for (int i = 0; i < n; i++) {
      System.out.print(format);
    }
  }

  private static void printBlank(int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(" ");
    }
  }

  private static void back(int back) {
    for (int i = 0; i < back; i++) {
      System.out.print("\b");
    }
  }
}
