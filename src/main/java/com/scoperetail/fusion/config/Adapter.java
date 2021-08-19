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

import static com.scoperetail.fusion.config.Adapter.TransformationType.NONE;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Adapter {

  public Adapter() {
    transformationType = NONE;
  }

  public enum AdapterType {
    INBOUND,
    OUTBOUND
  }

  public enum TransportType {
    JMS,
    REST,
    MAIL,
    KAFKA,
  }

  public enum MessageType {
    XML,
    JSON,
  }

  public enum TransformationType {
    DOMAIN_EVENT_FTL_TRANSFORMER,
    DOMAIN_EVENT_VELOCITY_TRANSFORMER,
    FTL_TEMPLATE_TRANSFORMER,
    VELOCITY_TEMPLATE_TRANSFORMER,
    NONE
  }

  public enum UsecaseResult {
    FAILURE,
    SUCCESS,
  }

  //COMMON
  private TransformationType transformationType;
  private AdapterType adapterType;
  private TransportType trasnportType;
  private UsecaseResult usecaseResult;
  // JMS & KAFKA
  private String template;
  private String brokerId;
  private String errorQueName;
  private String topicName;
  private String readConcurrency;
  //BO
  private String boBrokerId;
  private String boQueueName;
  // REST
  private String hostName;
  private String methodType;
  private Integer port;
  private String protocol;
  private String queueName;
  private String requestBodyTemplate;
  private String templateCustomizer;
  private String requestHeaderTemplate;
  private String uriTemplate;
  // MAIL
  private String hostId;
  private String fromTemplate;
  private String replyToTemplate;
  private String toTemplate;
  private String ccTemplate;
  private String bccTemplate;
  private String sentDateTemplate;
  private String subjectTemplate;
  private String textTemplate;
  //Inbound
  private MessageType messageType;
  private final List<String> messageIdentifiers = new ArrayList<>(1);
}
