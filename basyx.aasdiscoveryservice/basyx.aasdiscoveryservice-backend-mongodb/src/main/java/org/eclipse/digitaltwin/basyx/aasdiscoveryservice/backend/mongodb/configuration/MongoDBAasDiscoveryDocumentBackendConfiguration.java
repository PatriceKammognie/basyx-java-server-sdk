/*******************************************************************************
 * Copyright (C) 2025 the Eclipse BaSyx Authors
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 * SPDX-License-Identifier: MIT
 ******************************************************************************/

package org.eclipse.digitaltwin.basyx.aasdiscoveryservice.backend.mongodb.configuration;

import org.eclipse.digitaltwin.basyx.aasdiscoveryservice.backend.AasDiscoveryDocument;
import org.eclipse.digitaltwin.basyx.aasdiscoveryservice.backend.AasDiscoveryDocumentBackend;
import org.eclipse.digitaltwin.basyx.common.mongocore.MappingEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 
 * Provides the MongoDB configuration for the {@link AasDiscoveryDocumentBackend}
 * 
 * @author mateusmolina
 *
 */
@Configuration
@ConditionalOnExpression("'${basyx.backend}'.equals('MongoDB')")
@EnableMongoRepositories(basePackages = "org.eclipse.digitaltwin.basyx.aasdiscoveryservice.backend.mongodb.backend")
@EntityScan(basePackages = "org.eclipse.digitaltwin.basyx.aasdiscoveryservice.backend.mongodb.backend")
public class MongoDBAasDiscoveryDocumentBackendConfiguration {

	static final String COLLECTION_NAME_FIELD = "basyx.aasdiscoveryservice.mongodb.collectionName";
	static final String DEFAULT_COLLECTION_NAME = "aasdiscovery-service";

	@Bean
	MappingEntry aasDiscoveryDocumentMappingEntry(@Value("${" + COLLECTION_NAME_FIELD + ":" + DEFAULT_COLLECTION_NAME + "}") String collectionName) {
		return MappingEntry.of(collectionName, AasDiscoveryDocument.class);
	}

}
