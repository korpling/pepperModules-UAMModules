/**
 * Copyright 2009 Humboldt-Universität zu Berlin, INRIA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */
package org.corpus_tools.peppermodules.uam.impl;

import org.corpus_tools.peppermodules.model.uam.Layer;
import org.corpus_tools.peppermodules.model.uam.Segment;
import org.corpus_tools.peppermodules.model.uam.Text;
import org.corpus_tools.peppermodules.model.uam.UAMPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getSourceText <em>Source Text</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getLayer <em>Layer</em>}</li>
 *   <li>{@link org.corpus_tools.peppermodules.uam.impl.SegmentImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SegmentImpl extends EObjectImpl implements Segment {
	/**
	 * The default value of the '{@link #getFeatures() <em>Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected static final String FEATURES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected String features = FEATURES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSourceText() <em>Source Text</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceText()
	 * @generated
	 * @ordered
	 */
	protected Text sourceText;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Integer START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Integer start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Integer END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Integer end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final String STATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected String state = STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UAMPackage.Literals.SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFeatures(String newFeatures) {
		String oldFeatures = features;
		features = newFeatures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__FEATURES, oldFeatures, features));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Text getSourceText() {
		if (sourceText != null && sourceText.eIsProxy()) {
			InternalEObject oldSourceText = (InternalEObject)sourceText;
			sourceText = (Text)eResolveProxy(oldSourceText);
			if (sourceText != oldSourceText) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UAMPackage.SEGMENT__SOURCE_TEXT, oldSourceText, sourceText));
			}
		}
		return sourceText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Text basicGetSourceText() {
		return sourceText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceText(Text newSourceText) {
		Text oldSourceText = sourceText;
		sourceText = newSourceText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__SOURCE_TEXT, oldSourceText, sourceText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(Integer newStart) {
		Integer oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(Integer newEnd) {
		Integer oldEnd = end;
		end = newEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__END, oldEnd, end));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Layer getLayer() {
		if (eContainerFeatureID() != UAMPackage.SEGMENT__LAYER) return null;
		return (Layer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLayer(Layer newLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLayer, UAMPackage.SEGMENT__LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayer(Layer newLayer) {
		if (newLayer != eInternalContainer() || (eContainerFeatureID() != UAMPackage.SEGMENT__LAYER && newLayer != null)) {
			if (EcoreUtil.isAncestor(this, newLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLayer != null)
				msgs = ((InternalEObject)newLayer).eInverseAdd(this, UAMPackage.LAYER__SEGMENTS, Layer.class, msgs);
			msgs = basicSetLayer(newLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__LAYER, newLayer, newLayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(String newState) {
		String oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UAMPackage.SEGMENT__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UAMPackage.SEGMENT__LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLayer((Layer)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UAMPackage.SEGMENT__LAYER:
				return basicSetLayer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UAMPackage.SEGMENT__LAYER:
				return eInternalContainer().eInverseRemove(this, UAMPackage.LAYER__SEGMENTS, Layer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UAMPackage.SEGMENT__FEATURES:
				return getFeatures();
			case UAMPackage.SEGMENT__SOURCE_TEXT:
				if (resolve) return getSourceText();
				return basicGetSourceText();
			case UAMPackage.SEGMENT__START:
				return getStart();
			case UAMPackage.SEGMENT__END:
				return getEnd();
			case UAMPackage.SEGMENT__ID:
				return getId();
			case UAMPackage.SEGMENT__LAYER:
				return getLayer();
			case UAMPackage.SEGMENT__STATE:
				return getState();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UAMPackage.SEGMENT__FEATURES:
				setFeatures((String)newValue);
				return;
			case UAMPackage.SEGMENT__SOURCE_TEXT:
				setSourceText((Text)newValue);
				return;
			case UAMPackage.SEGMENT__START:
				setStart((Integer)newValue);
				return;
			case UAMPackage.SEGMENT__END:
				setEnd((Integer)newValue);
				return;
			case UAMPackage.SEGMENT__ID:
				setId((String)newValue);
				return;
			case UAMPackage.SEGMENT__LAYER:
				setLayer((Layer)newValue);
				return;
			case UAMPackage.SEGMENT__STATE:
				setState((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UAMPackage.SEGMENT__FEATURES:
				setFeatures(FEATURES_EDEFAULT);
				return;
			case UAMPackage.SEGMENT__SOURCE_TEXT:
				setSourceText((Text)null);
				return;
			case UAMPackage.SEGMENT__START:
				setStart(START_EDEFAULT);
				return;
			case UAMPackage.SEGMENT__END:
				setEnd(END_EDEFAULT);
				return;
			case UAMPackage.SEGMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case UAMPackage.SEGMENT__LAYER:
				setLayer((Layer)null);
				return;
			case UAMPackage.SEGMENT__STATE:
				setState(STATE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UAMPackage.SEGMENT__FEATURES:
				return FEATURES_EDEFAULT == null ? features != null : !FEATURES_EDEFAULT.equals(features);
			case UAMPackage.SEGMENT__SOURCE_TEXT:
				return sourceText != null;
			case UAMPackage.SEGMENT__START:
				return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
			case UAMPackage.SEGMENT__END:
				return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
			case UAMPackage.SEGMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UAMPackage.SEGMENT__LAYER:
				return getLayer() != null;
			case UAMPackage.SEGMENT__STATE:
				return STATE_EDEFAULT == null ? state != null : !STATE_EDEFAULT.equals(state);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (features: ");
		result.append(features);
		result.append(", start: ");
		result.append(start);
		result.append(", end: ");
		result.append(end);
		result.append(", id: ");
		result.append(id);
		result.append(", state: ");
		result.append(state);
		result.append(')');
		return result.toString();
	}

} //SegmentImpl
