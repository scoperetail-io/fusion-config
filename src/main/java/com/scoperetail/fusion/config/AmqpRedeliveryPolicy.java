package com.scoperetail.fusion.config;

/*-
 * *****
 * fusion-messaging
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

public class AmqpRedeliveryPolicy {
  private Double backOffMultiplier;
  private Integer maxDeliveries;
  private String queueNameRegex;
  private Long redeliveryDelay;
  private Boolean useExponentialBackOff;
  private Long maxDeliveryDelay;
  private Integer initialRedeliveryDelay;

  public AmqpRedeliveryPolicy() {
    backOffMultiplier = 1.0;
    maxDeliveries = 3;
    queueNameRegex = "*";
    redeliveryDelay = 3000l;
    useExponentialBackOff = true;
    maxDeliveryDelay = 3600000l;
    initialRedeliveryDelay = 0;
  }

  public Double getBackOffMultiplier() {
    return backOffMultiplier;
  }

  public void setBackOffMultiplier(final Double backOffMultiplier) {
    this.backOffMultiplier = backOffMultiplier;
  }

  public Integer getMaxDeliveries() {
    return maxDeliveries;
  }

  public void setMaxDeliveries(final Integer maxDeliveries) {
    this.maxDeliveries = maxDeliveries;
  }

  public String getQueueNameRegex() {
    return queueNameRegex;
  }

  public void setQueueNameRegex(final String queueNameRegex) {
    this.queueNameRegex = queueNameRegex;
  }

  public Long getRedeliveryDelay() {
    return redeliveryDelay;
  }

  public void setRedeliveryDelay(final Long redeliveryDelay) {
    this.redeliveryDelay = redeliveryDelay;
  }

  public Boolean getUseExponentialBackOff() {
    return useExponentialBackOff;
  }

  public void setUseExponentialBackOff(final Boolean useExponentialBackOff) {
    this.useExponentialBackOff = useExponentialBackOff;
  }

  public Long getMaxDeliveryDelay() {
    return maxDeliveryDelay;
  }

  public void setMaxDeliveryDelay(final Long maxDeliveryDelay) {
    this.maxDeliveryDelay = maxDeliveryDelay;
  }

  public Integer getInitialRedeliveryDelay() {
    return initialRedeliveryDelay;
  }

  public void setInitialRedeliveryDelay(final Integer initialRedeliveryDelay) {
    this.initialRedeliveryDelay = initialRedeliveryDelay;
  }
}
