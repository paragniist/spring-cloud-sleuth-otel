/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sleuth.otel.bridge;

import java.net.URI;

import io.opentelemetry.instrumentation.api.instrumenter.net.NetServerAttributesGetter;

import org.springframework.cloud.sleuth.http.HttpServerRequest;
import org.springframework.lang.Nullable;

/**
 * Extracts OpenTelemetry network semantic attributes value for server http spans.
 *
 * @author Nikita Salnikov-Tarnovski
 */
class HttpRequestNetServerAttributesExtractor implements NetServerAttributesGetter<HttpServerRequest> {

	@Nullable
	@Override
	public String getTransport(HttpServerRequest httpRequest) {
		String url = httpRequest.url();
		if (url == null) {
			return null;
		}
		URI uri = URI.create(url);
		return uri.getScheme();
	}

	@Nullable
	@Override
	public String getHostName(HttpServerRequest httpRequest) {
		String url = httpRequest.url();
		if (url == null) {
			return null;
		}
		URI uri = URI.create(url);
		return uri.getHost();
	}

	@Override
	public Integer getHostPort(HttpServerRequest httpRequest) {
		return httpRequest.remotePort();
	}

	@Nullable
	@Override
	public String getSockPeerAddr(HttpServerRequest httpRequest) {
		return httpRequest.remoteIp();
	}

	@Nullable
	@Override
	public Integer getSockPeerPort(HttpServerRequest httpRequest) {
		return httpRequest.remotePort();
	}

}
