/* ScopeRetail (C)2021 */
package com.scoperetail.fusion.config;

/*-
 * *****
 * fusion-config
 * -----
 * Copyright (C) 2018 - 2021 Scope Retail Systems Inc.
 * -----
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.scoperetail.fusion.config.Adapter.AdapterType;
import lombok.Data;

@Component
@ConfigurationProperties(prefix = "fusion")
@Data
public class FusionConfig {
  private final List<Broker> brokers = new ArrayList<>();
  private RestRetryPolicy restRetryPolicy;
  private final List<UseCaseConfig> usecases = new ArrayList<>();
  private Credentials credentials;
  private final List<MailHost> mailHosts = new ArrayList<>();

  private Map<String, Broker> brokersByBrokerIdMap = new HashMap<>(1);
  private Map<String, UseCaseConfig> usecasesByNameMap = new HashMap<>(1);
  private Map<String, Optional<Config>> activeConfigByNameMap = new HashMap<>(1);
  private Map<String, Optional<Adapter>> inboundAdapterByNameMap = new HashMap<>(1);

  @PostConstruct
  public void init() {
    brokersByBrokerIdMap =
        brokers.stream().collect(Collectors.toMap(Broker::getBrokerId, Function.identity()));
    usecasesByNameMap =
        usecases.stream().collect(Collectors.toMap(UseCaseConfig::getName, Function.identity()));
    activeConfigByNameMap =
        usecases
            .stream()
            .collect(
                Collectors.toMap(
                    UseCaseConfig::getName,
                    usecase ->
                        usecase
                            .getConfigs()
                            .stream()
                            .filter(config -> config.getName().equals(usecase.getActiveConfig()))
                            .findFirst()));
    inboundAdapterByNameMap =
        activeConfigByNameMap
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry ->
                        entry
                            .getValue()
                            .flatMap(
                                config ->
                                    config
                                        .getAdapters()
                                        .stream()
                                        .filter(
                                            adapter ->
                                                adapter
                                                    .getAdapterType()
                                                    .equals(AdapterType.INBOUND))
                                        .findFirst())));
  }

  public Optional<Broker> getBroker(final String brokerId) {
    return Optional.ofNullable(brokersByBrokerIdMap.get(brokerId));
  }

  public Optional<UseCaseConfig> getUsecase(final String usecaseName) {
    return Optional.ofNullable(usecasesByNameMap.get(usecaseName));
  }

  public Optional<Config> getActiveConfig(final String usecaseName) {
    return activeConfigByNameMap.get(usecaseName);
  }

  public Optional<Adapter> getInboundAdapter(final String usecaseName) {
    return inboundAdapterByNameMap.get(usecaseName);
  }
}
