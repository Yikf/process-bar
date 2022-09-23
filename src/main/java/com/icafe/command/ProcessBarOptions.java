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

import picocli.CommandLine.*;

import java.util.concurrent.Callable;

@Command(name = "ProcessBar command line.\n\n", description = "[OPTIONS]:", mixinStandardHelpOptions = true)
public class ProcessBarOptions implements Callable<Integer> {

  @Option(description = "A source class, it provide data for print to bar.", names = {"-sc", "--sourceClass"}, required = true)
  private String sourceClass;

  @Option(description = "The prefix character of the progress bar.", names = {"-p", "--prefix"}, defaultValue = "[")
  private String prefix;

  @Option(description = "The suffix character of the progress bar.", names = {"-s", "--suffix"}, defaultValue = "]")
  private String suffix;

  @Option(description = "The format character of the progress bar.", names = {"-f", "--format"}, defaultValue = "#")
  private String format;

  @Option(description = "How often the progress bar is refreshed, unit second", names = {"-i", "--interval"}, defaultValue = "1")
  private int interval;

  @Override
  public Integer call() throws Exception {

    assert(format.length() == 1);

    ProcessBar dataSource = ProcessBarUtils.supply(sourceClass);

    ProcessBarUtils.render(prefix, suffix, format, interval, dataSource);
    return 0;
  }
}
