/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.connect.paimon.utils;

import org.apache.paimon.catalog.Catalog;
import org.apache.paimon.catalog.CatalogContext;
import org.apache.paimon.catalog.CatalogFactory;
import org.apache.paimon.catalog.Identifier;
import org.apache.paimon.options.Options;
import org.apache.paimon.schema.Schema;
import org.apache.paimon.schema.SchemaChange;

import java.util.List;
import java.util.Map;

/** Utils for paimon catalog. */
public class CatalogUtils {

    public static Catalog createCataLog(Map<String, String> catalogProps) {
        Options options = new Options(catalogProps);
        CatalogContext context = CatalogContext.create(options);
        return CatalogFactory.createCatalog(context);
    }

    public static void createDatabase(Catalog catalog, String databaseName, boolean ignoreIfExists)
            throws Catalog.DatabaseAlreadyExistException {
        catalog.createDatabase(databaseName, ignoreIfExists);
    }

    public static void createTable(
            Catalog catalog, Schema schema, Identifier identifier, boolean ignoreIfExists)
            throws Catalog.TableAlreadyExistException, Catalog.DatabaseNotExistException {
        catalog.createTable(identifier, schema, ignoreIfExists);
    }

    public static void alterTable(
            Catalog catalog,
            Identifier identifier,
            List<SchemaChange> schemaChanges,
            boolean ignoreIfNotExists)
            throws Catalog.ColumnAlreadyExistException, Catalog.TableNotExistException,
                    Catalog.ColumnNotExistException {
        catalog.alterTable(identifier, schemaChanges, ignoreIfNotExists);
    }
}
