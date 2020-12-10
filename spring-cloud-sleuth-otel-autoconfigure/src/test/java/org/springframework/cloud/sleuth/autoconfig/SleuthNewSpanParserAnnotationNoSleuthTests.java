/*
 * Copyright 2013-2020 the original author or authors.
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

package org.springframework.cloud.sleuth.autoconfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.NewSpanParser;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SleuthAnnotationConfiguration.class, properties = "spring.sleuth.enabled=false")
public class SleuthNewSpanParserAnnotationNoSleuthTests {

	@Autowired(required = false)
	NewSpanParser newSpanParser;

	@Autowired(required = false)
	Tracer tracer;

	@Test
	public void shouldNotAutowireBecauseConfigIsDisabled() {
		assertThat(this.newSpanParser).isNull();
		Assertions.assertThat(this.tracer).isNull();
	}

}
