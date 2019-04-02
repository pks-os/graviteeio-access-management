/*
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { AppConfig } from "../../../../config/app.config";
import { AuditService } from "../../../services/audit.service";

@Component({
  selector: 'app-audits',
  templateUrl: './audits.component.html',
  styleUrls: ['./audits.component.scss']
})
export class AuditsComponent implements OnInit {
  audits: any[];
  pagedAudits: any;
  domainId: string;
  page: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private auditService: AuditService) {
    this.page.pageNumber = 0;
    this.page.size = 25;
  }

  ngOnInit() {
    this.domainId = this.route.snapshot.parent.parent.params['domainId'];
    if (this.router.routerState.snapshot.url.startsWith('/settings')) {
      this.domainId = AppConfig.settings.authentication.domainId;
    }

    this.pagedAudits = this.route.snapshot.data['audits'];
    this.audits = this.pagedAudits.data;
    this.page.totalElements = this.pagedAudits.totalCount;
  }


  loadAudits() {
    this.auditService.findByDomain(this.domainId, this.page.pageNumber, this.page.size).map(res => res.json()).subscribe(pagedAudits => {
      this.page.totalElements = pagedAudits.totalCount;
      this.audits = pagedAudits.data;
    });
  }

  get isEmpty() {
    return !this.audits || this.audits.length == 0;
  }

  setPage(pageInfo){
    this.page.pageNumber = pageInfo.offset;
    this.loadAudits();
  }

}
