insert into reprop(entryId, propertyName, propval, entryId) values((select randomUUID() from dual),
  'vp.oc-scart.showElectricalNumber',
  (select propval from reprop where propertyName = 'showElectricalNumber' and entryId like
  (select entryId from reentry where reentry.NAME like 'ree/pxc_gwis_vp_lookup_ae')),
  (select entryId from reentry where reentry.NAME like 'ree/pxc_gwis_vp_lookup_ae'));